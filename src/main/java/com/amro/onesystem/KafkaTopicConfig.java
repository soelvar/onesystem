package com.amro.onesystem;

import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.validation.annotation.Validated;

@Profile("!kafkaless")
@Validated
@Configuration
public class KafkaTopicConfig {

  @Bean
  public KafkaAdmin kafkaAdmin(@NotBlank @Value(value = "${kafka.bootstrapAddress}") final String bootstrapAddress) {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    return new KafkaAdmin(configs);
  }

  @Bean
  public NewTopic onesystemTopic(
      @NotBlank @Value(value = "${kafka.topics.onesystemTopic.name}") final String topicName,
      @NotNull @Value(value = "${kafka.topics.onesystemTopic.numPartitions}") final Integer numPartitions,
      @NotNull @Value(value = "${kafka.topics.onesystemTopic.replicationFactor}") final Short replicationFactor
  ) {
    return new NewTopic(topicName, numPartitions, replicationFactor);
  }
}
