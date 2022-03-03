package com.poc.kafka.info.serialization;

import com.poc.kafka.info.model.ProductDataEntity;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class ProductDataSerde implements Serde<ProductDataEntity>
{

  private final ProductDataSerializer serialize = new ProductDataSerializer();
  private final ProductDataDeserializer deserialize = new ProductDataDeserializer();

  @Override
  public Serializer<ProductDataEntity> serializer() {
    return serialize;
  }

  @Override
  public Deserializer<ProductDataEntity> deserializer() {
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
