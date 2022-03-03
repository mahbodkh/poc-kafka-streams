package com.poc.kafka.info.serialization;

import com.poc.kafka.info.model.ProductDataEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.Closeable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ProductDataDeserializer implements Closeable,
                                                AutoCloseable,
                                                Deserializer<ProductDataEntity>
{
  private static final Charset CHARSET = StandardCharsets.UTF_8;
  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    Deserializer.super.configure(configs, isKey);
  }

  @Override
  public ProductDataEntity deserialize(String topic, byte[] data) {
    try {
      final var machine = new String(data, CHARSET);

      return mapper.readValue(machine, ProductDataEntity.class);
    }
    catch (JsonProcessingException e) {
      throw new IllegalArgumentException("Error reading bytes", e);
    }
  }

  @Override
  public ProductDataEntity deserialize(String topic, Headers headers, byte[] data) {
    return Deserializer.super.deserialize(topic, headers, data);
  }

  @Override
  public void close() {
    Deserializer.super.close();
  }
}
