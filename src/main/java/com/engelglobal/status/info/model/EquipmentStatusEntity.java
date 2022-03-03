package com.engelglobal.status.info.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentStatusEntity
{
  @JsonProperty("productId")
  private String productId;
  @JsonProperty("status")
  private StatusTypes status;

  public enum StatusTypes
  {
    ONLINE,
    OFFLINE,
    PRODUCING,
    ERROR
  }
}
