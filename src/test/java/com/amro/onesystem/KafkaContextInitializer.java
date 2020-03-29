package com.amro.onesystem;


import static com.amro.onesystem.ApplicationWithKafkaIT.KAFKA_CONTAINER;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
public class KafkaContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  @Override
  public void initialize(ConfigurableApplicationContext applicationContext) {
    TestPropertyValues.of(
        "kafka.bootstrapAddress=" + KAFKA_CONTAINER.getBootstrapServers())
        .applyTo(applicationContext);
  }


}

