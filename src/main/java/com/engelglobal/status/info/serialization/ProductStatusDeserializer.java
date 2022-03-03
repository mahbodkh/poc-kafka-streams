package com.engelglobal.status.info.serialization;

import com.engelglobal.status.info.model.ProductStatusEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RequiredArgsConstructor
public class ProductStatusDeserializer implements Closeable,
                                                  AutoCloseable,
                                                  Deserializer<ProductStatusEntity>
{
  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    Deserializer.super.configure(configs, isKey);
  }

  @Override
  public ProductStatusEntity deserialize(String topic, byte[] data) {
    try {
      final var machine = new String(data, StandardCharsets.UTF_8);
      //                    .replaceAll("[\\x00-\\x09\\x0B\\x0C\\x0E-\\x1F\\x7F]", "");

      return mapper.readValue(machine, ProductStatusEntity.class);
    }
    catch (IOException e) {
      throw new IllegalArgumentException("Error reading bytes", e);
    }
  }

  @Override
  public ProductStatusEntity deserialize(String topic, Headers headers, byte[] data) {
    return Deserializer.super.deserialize(topic, headers, data);
  }

  @Override
  public void close() {
    Deserializer.super.close();
  }
}
