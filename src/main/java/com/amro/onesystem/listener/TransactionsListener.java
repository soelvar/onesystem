package com.amro.onesystem.listener;

import com.amro.onesystem.service.TransactionService;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
public class TransactionsListener {

  private final TransactionService transactionService;

  public TransactionsListener(@NotNull final TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @KafkaListener(topics = "#{'${kafka.topics.onesystemTopic.name}'}", groupId = "#{'${kafka.consumers.transactionsConsumer.groupId}'}")
  public void listen(String message) {
    log.info("Received Messasge: {}", message);
    transactionService.saveTransactionMessage(message);
  }
}
