package com.amro.onesystem;

import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.validation.annotation.Validated;

@Profile("!kafkaless")
@Validated
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

  private final String bootstrapAddress;

  @Autowired
  public KafkaConsumerConfig(@NotBlank @Value(value = "${kafka.bootstrapAddress}") final String bootstrapAddress) {
    this.bootstrapAddress = bootstrapAddress;
  }

  public ConsumerFactory<String, String> consumerFactory(final String groupId) {

    Map<String, Object> props = new HashMap<>();
    props.put(
        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
        bootstrapAddress);
    props.put(
        ConsumerConfig.GROUP_ID_CONFIG,
        groupId);
    props.put(
        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class);
    props.put(
        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class);
    return new DefaultKafkaConsumerFactory<>(props);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
      @NotBlank @Value(value = "${kafka.consumers.transactionsConsumer.groupId}") final String groupId) {

    ConcurrentKafkaListenerContainerFactory<String, String> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory(groupId));
    return factory;
  }
}