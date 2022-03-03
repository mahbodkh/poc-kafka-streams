package com.engelglobal.status.info.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDataEntity
{
  @JsonProperty("productId")
  private String productId;
  @JsonProperty("productName")
  private String productName;
  @JsonProperty("details")
  private List<Detail> details;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Detail
  {
    @JsonProperty("timestamp")
    private String timestamp;
  }
}