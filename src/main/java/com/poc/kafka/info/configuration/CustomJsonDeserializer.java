package com.poc.kafka.info.configuration;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.kafka.support.serializer.JsonSerde;

public class CustomJsonDeserializer extends JsonSerde<JsonNode> {

    public CustomJsonDeserializer() {
        super(JsonMapper.builder().enable(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS).build());
    }
}
