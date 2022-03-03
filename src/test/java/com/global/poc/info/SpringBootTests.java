package com.engelglobal.status.info;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;

/**
 * This test initializes the spring boot context, so all configurations and beans get loaded and instantiated.<br>/
 * By doing so, it's verified the spring boot context is able to run.<br>/
 * Need to mock away Kafka connection by using EmbeddedKafka, otherwise the KafkaStreamApplication would
 * fail to start due to missing connection which fails the spring boot initialization.<br>/
 * Need to configure topics with 1 partition, as the EmbeddedKafka is a single broker.<br/>
 * Kafka connection settings are configured with TestPropertySource.<br>/
 * Eureka service registration is configured disabled with TestPropertySource.<br>/
 */
@SpringBootTest
@EmbeddedKafka(
    topics = {
        "${transaction-service.kafka.topicOutboxTransactionStatus}",
        "${transaction-service.kafka.topicScpiOutSapTransaction}",
        "${transaction-service.kafka.topicScpiInSapTransaction}"
    },
    partitions = 1,
    bootstrapServersProperty = "spring.kafka.properties.bootstrap.servers",
    controlledShutdown = true)
@TestPropertySource(
    properties = {
        "spring.kafka.properties.security.protocol=PLAINTEXT",
        "eureka.client.enabled=false",
        "eureka.client.fetch-registry=false",
        "eureka.client.register-with-eureka=false",
        "ribbon.eureka.enabled=false"
    })
class SpringBootTests
{
  @Test
  void test() {
    System.out.println("dummy test");
  }
}
