package com.amro.onesystem.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.amro.onesystem.ApplicationWithoutKafkaIT;
import com.amro.onesystem.domain.Transaction;
import com.amro.onesystem.domain.repository.TransactionRepository;
import java.util.Optional;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionServiceIT extends ApplicationWithoutKafkaIT {

  private static final String TRANSACTION_MESSAGE = "315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012380     688032000092500000000             O";

  @Autowired
  private TransactionService transactionService;

  @Autowired
  private TransactionRepository transactionRepository;

  @Test
  public void shouldFindTransactionById() {
    Transaction transaction = transactionService.saveTransactionMessage(TRANSACTION_MESSAGE);

    Optional<Transaction> optionalTransaction = transactionRepository.findById(transaction.getId());

    assertThat(optionalTransaction.isPresent(), is(true));
    assertThat(optionalTransaction.get(), is(transaction));

    transactionRepository.deleteAll();
  }


}