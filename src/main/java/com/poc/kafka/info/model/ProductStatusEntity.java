package com.poc.kafka.info.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductStatusEntity
{
  @JsonProperty("timestamp")
  private String timestamp;
  @JsonProperty("uniqid")
  private String uniqId;
  @JsonProperty("status")
  private ProductStatusTypes status;

  public enum ProductStatusTypes
  {
    NEW,
    ONLINE,
    OFFLINE,
    RUNNING,
    ERROR,
    UNKNOWN
  }
}
