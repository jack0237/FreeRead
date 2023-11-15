package com.sklfgroup.auxillium.rest.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sklfgroup.auxillium.exceptions.BadRequestException;
import com.sklfgroup.auxillium.exceptions.ExceptionTypes;
import com.sklfgroup.auxillium.helpers.CustomHeaders;
import com.sklfgroup.auxillium.rest.dto.responses.error.ErrorResponse;
import com.sklfgroup.auxillium.rest.dto.responses.error.ErrorsResponse;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.sklfgroup.auxillium.helpers.CustomHeaders.*;
import static java.lang.String.format;

@Component
public class CorrelationIdInterceptor implements Filter {

  public static final String CORRELATION_ID = "correlationId";

  @Value("#{'${app.enabled-operators:YG}'.toLowerCase().split(',')}")
  private Set<String> enabledOperators;

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    try {
      HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
      String operatorId = httpServletRequest.getHeader(OPERATOR_ID);
      String correlationId =
          StringUtils.hasText(httpServletRequest.getHeader(CustomHeaders.CORRELATION_ID))
              ? httpServletRequest.getHeader(CustomHeaders.CORRELATION_ID)
              : UUID.randomUUID().toString();

      MDC.put(CORRELATION_ID, correlationId);
      httpServletRequest.setAttribute(CustomHeaders.CORRELATION_ID, correlationId);

      HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
      httpServletResponse.setHeader(CustomHeaders.CORRELATION_ID, correlationId);

      if (StringUtils.hasText(operatorId)) {
        httpServletResponse.setHeader(OPERATOR_ID, operatorId);
        if (!enabledOperators.contains(operatorId.toLowerCase())) {
          throw new BadRequestException(
              format("Operator %s is not available", operatorId),
              ExceptionTypes.INVALID_OPERATOR_ID);
        }
      }

      filterChain.doFilter(httpServletRequest, httpServletResponse);
    } catch (BadRequestException exception) {

      final List<ErrorResponse> errors =
          Collections.singletonList(
              new ErrorResponse(
                  MDC.get(CORRELATION_ID),
                  "400",
                  exception.getType().name(),
                  exception.getCode().name(),
                  exception.getMessage()));

      final HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
      httpServletResponse.setHeader(CONTENT_TYPE, APPLICATION_TYPE_JSON);
      ((HttpServletResponse) servletResponse).setStatus(HttpStatus.BAD_REQUEST.value());
      final ObjectMapper mapper = new ObjectMapper();
      httpServletResponse.getWriter().write(mapper.writeValueAsString(new ErrorsResponse(errors)));
    } finally {
      MDC.remove(CORRELATION_ID);
    }
  }
}
