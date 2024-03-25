package com.sklfgroup.auxillium.rest.interceptors;

import com.sklfgroup.auxillium.helpers.CustomHeaders;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class AuthorizationHeaderInterceptor implements Filter {

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws ServletException, IOException {
    final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    MDC.put(CustomHeaders.AUTHORIZATION, httpServletRequest.getHeader(CustomHeaders.AUTHORIZATION));
    filterChain.doFilter(httpServletRequest, servletResponse);
  }
}
