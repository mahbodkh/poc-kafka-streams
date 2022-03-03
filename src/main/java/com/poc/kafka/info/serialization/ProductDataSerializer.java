package com.poc.kafka.info.serialization;

import com.poc.kafka.info.model.ProductDataEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.io.Closeable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

public class ProductDataSerializer implements Closeable,
                                              AutoCloseable,
                                              Serializer<ProductDataEntity>
{
  private static final Charset CHARSET = StandardCharsets.UTF_8;
  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    Serializer.super.configure(configs, isKey);
  }

  @Override
  public byte[] serialize(String topic, ProductDataEntity data) {
    String json = null;
    try {
      json = mapper.writeValueAsString(data);
      //                    .replaceAll("[\\x00-\\x09\\x0B\\x0C\\x0E-\\x1F\\x7F]", "");
    }
    catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return Objects.requireNonNull(json).getBytes(CHARSET);
  }

  @Override
  public byte[] serialize(String topic, Headers headers, ProductDataEntity data) {
    return Serializer.super.serialize(topic, headers, data);
  }

  @Override
  public void close() {
    Serializer.super.close();
  }
}
