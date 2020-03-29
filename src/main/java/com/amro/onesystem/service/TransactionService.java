package com.amro.onesystem.service;

import com.amro.onesystem.domain.Transaction;
import com.amro.onesystem.domain.repository.TransactionRepository;
import com.amro.onesystem.listener.converter.TransactionMessageToTransactionConverter;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

  private final TransactionRepository transactionRepository;

  private final TransactionMessageToTransactionConverter transactionMessageToTransactionConverter;

  public TransactionService(@NotNull final TransactionRepository transactionRepository,
      @NotNull final TransactionMessageToTransactionConverter transactionMessageToTransactionConverter) {
    this.transactionRepository = transactionRepository;
    this.transactionMessageToTransactionConverter = transactionMessageToTransactionConverter;
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public Transaction saveTransactionMessage(String transactionMessage) {
    Transaction transaction = transactionMessageToTransactionConverter.convert(transactionMessage);
    return transactionRepository.save(transaction);
  }
}
