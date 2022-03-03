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
  @JsonProperty("data")
  private List<Data> datas;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Data
  {
    @JsonProperty("timestamp")
    private String timestamp;
  }
}