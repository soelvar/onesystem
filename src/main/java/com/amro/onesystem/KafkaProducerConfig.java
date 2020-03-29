package com.amro.onesystem;

import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.validation.annotation.Validated;

@Profile("!kafkaless")
@Validated
@Configuration
public class KafkaProducerConfig {

  public ProducerFactory<String, String> producerFactory(final String bootstrapAddress) {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
        bootstrapAddress);
    configProps.put(
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class);
    configProps.put(
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class);
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate(@NotBlank @Value(value = "${kafka.bootstrapAddress}") final String bootstrapAddress) {
    return new KafkaTemplate<>(producerFactory(bootstrapAddress));
  }
}

