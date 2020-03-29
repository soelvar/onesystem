package com.amro.onesystem.listener;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import com.amro.onesystem.ApplicationWithKafkaIT;
import com.amro.onesystem.service.TransactionService;
import java.util.concurrent.ExecutionException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;

public class TransactionsListenerIT extends ApplicationWithKafkaIT {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Value(value = "${kafka.topics.onesystemTopic.name}")
  private String topicName;

  @MockBean
  private TransactionService transactionService;

  @Test
  public void shouldSendMessageToKafkaAndConsumeMessage() throws InterruptedException, ExecutionException {
    //wait for kafkatemplate to be ready
    Thread.sleep(2000L);
    kafkaTemplate.send(topicName, "test");
    verify(transactionService, timeout(5000).times(1)).saveTransactionMessage(eq("test"));
  }
}