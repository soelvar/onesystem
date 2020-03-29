package com.amro.onesystem;

import com.amro.onesystem.listener.TransactionsListener;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("kafkaless")
public abstract class ApplicationWithoutKafkaIT extends ApplicationIT {

  @MockBean
  protected KafkaTemplate<String, String> kafkaTemplate;

  @MockBean
  protected TransactionsListener transactionsListener;

}
