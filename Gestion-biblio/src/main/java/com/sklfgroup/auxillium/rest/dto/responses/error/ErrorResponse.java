package com.sklfgroup.auxillium.rest.dto.responses.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ErrorResponse {
  private String id;
  private String status;
  private String title;
  private String code;
  private String detail;
}
