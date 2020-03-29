package com.amro.onesystem;

import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.KafkaContainer;

@ContextConfiguration(initializers = {KafkaContextInitializer.class})
public abstract class ApplicationWithKafkaIT extends ApplicationIT {

  public static final KafkaContainer KAFKA_CONTAINER;

  static {
    KAFKA_CONTAINER = new KafkaContainer("4.1.2");
    KAFKA_CONTAINER.start();
  }
}
