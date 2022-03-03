package com.engelglobal.status.info.serialization;

import com.engelglobal.status.info.model.EquipmentStatusEntity;
import org.springframework.beans.factory.annotation.Configurable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

@Configurable
public class EquipmentStatusSerde implements
                                  Closeable,
                                  AutoCloseable,
                                  Serializer<EquipmentStatusEntity>,
                                  Deserializer<JsonNode>,
                                  Serde<EquipmentStatusEntity>
{

  @Override
  public JsonNode deserialize(String topic, byte[] data) {
    final var mapper = new ObjectMapper();
    final var value = new String(data, StandardCharsets.UTF_8);
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    JsonNode jsonNodeRoot = null;
    try {
      jsonNodeRoot = mapper.readTree(value);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
    return jsonNodeRoot;
  }

  @Override
  public JsonNode deserialize(String topic, Headers headers, byte[] data) {
    return deserialize(topic, data);
  }

  @Override
  public Serializer<EquipmentStatusEntity> serializer() {
    return new EquipmentStatusSerde();
  }

  @Override
  public Deserializer deserializer() {
    return new EquipmentStatusSerde();
  }

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    //    Serializer.super.configure(configs, isKey);
  }

  @Override
  public byte[] serialize(String topic, EquipmentStatusEntity data) {
    String json = null;
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    mapper.registerModule(new Jdk8Module());
    try {
      json = mapper.writeValueAsString(data);
    }
    catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return Objects.requireNonNull(json).getBytes(StandardCharsets.UTF_8);
  }

  @Override
  public byte[] serialize(String topic, Headers headers, EquipmentStatusEntity data) {
    return serialize(topic, data);
  }

  @Override
  public void close() {
    Serializer.super.close();
    Deserializer.super.close();
  }
}

