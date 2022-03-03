package com.poc.kafka.info.serialization;

import com.poc.kafka.info.model.ProductStatusEntity;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class ProductStatusSerde implements Serde<ProductStatusEntity>
{

  private final ProductStatusSerializer serialize = new ProductStatusSerializer();
  private final ProductStatusDeserializer deserialize = new ProductStatusDeserializer();

  @Override
  public Serializer<ProductStatusEntity> serializer() {
    return serialize;
  }

  @Override
  public Deserializer<ProductStatusEntity> deserializer() {
    return deserialize;
  }

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    serialize.configure(configs, isKey);
    deserialize.configure(configs, isKey);
  }

  @Override
  public void close() {
    serialize.close();
    deserialize.close();
  }
}
