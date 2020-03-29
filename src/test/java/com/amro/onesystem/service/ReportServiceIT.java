package com.amro.onesystem.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.amro.onesystem.ApplicationWithoutKafkaIT;
import com.amro.onesystem.domain.Transaction;
import com.amro.onesystem.domain.repository.TransactionRepository;
import com.amro.onesystem.domain.repository.report.ClientProductTotalReportLine;
import java.time.LocalDate;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ReportServiceIT extends ApplicationWithoutKafkaIT {

  private static final String TRANSACTION_MESSAGE = "315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012380     688032000092500000000             O";

  @Autowired
  private TransactionService transactionService;

  @Autowired
  private ReportService reportService;

  @Autowired
  private TransactionRepository transactionRepository;

  @Test
  public void shouldRetrieveReportWhenNoTransactionDate() {
    Transaction transaction = transactionService.saveTransactionMessage(TRANSACTION_MESSAGE);

    List<ClientProductTotalReportLine> clientProductTotalReportLines = reportService.retrieveClientProductTotalReport();

    assertThat(clientProductTotalReportLines.size(), is(1));
    assertThat(clientProductTotalReportLines.get(0).getClientType(), is("CL"));
    assertThat(clientProductTotalReportLines.get(0).getClientNumber(), is(4321));
    assertThat(clientProductTotalReportLines.get(0).getAccountNumber(), is(0002));
    assertThat(clientProductTotalReportLines.get(0).getSubAccountNumber(), is(0001));
    assertThat(clientProductTotalReportLines.get(0).getExchangeCode(), is("SGX"));
    assertThat(clientProductTotalReportLines.get(0).getProductGroupCode(), is("FU"));
    assertThat(clientProductTotalReportLines.get(0).getSymbol(), is("NK"));
    assertThat(clientProductTotalReportLines.get(0).getExpirationDate(), is(LocalDate.of(2010, 9, 10)));
    assertThat(clientProductTotalReportLines.get(0).getTotal(), is(1L));

    transactionRepository.deleteAll();

  }

  @Test
  public void shouldRetrieveReportWhenTransactionDate() {
    Transaction transaction = transactionService.saveTransactionMessage(TRANSACTION_MESSAGE);

    List<ClientProductTotalReportLine> clientProductTotalReportLines = reportService
        .retrieveClientProductTotalReportByTransactionDate(LocalDate.of(2010, 8, 20));

    assertThat(clientProductTotalReportLines.size(), is(1));
    assertThat(clientProductTotalReportLines.get(0).getClientType(), is("CL"));
    assertThat(clientProductTotalReportLines.get(0).getClientNumber(), is(4321));
    assertThat(clientProductTotalReportLines.get(0).getAccountNumber(), is(0002));
    assertThat(clientProductTotalReportLines.get(0).getSubAccountNumber(), is(0001));
    assertThat(clientProductTotalReportLines.get(0).getExchangeCode(), is("SGX"));
    assertThat(clientProductTotalReportLines.get(0).getProductGroupCode(), is("FU"));
    assertThat(clientProductTotalReportLines.get(0).getSymbol(), is("NK"));
    assertThat(clientProductTotalReportLines.get(0).getExpirationDate(), is(LocalDate.of(2010, 9, 10)));
    assertThat(clientProductTotalReportLines.get(0).getTotal(), is(1L));

    transactionRepository.deleteAll();
  }

  @Test
  public void shouldRetrieveNoReportWhenWrongTransactionDate() {
    Transaction transaction = transactionService.saveTransactionMessage(TRANSACTION_MESSAGE);

    List<ClientProductTotalReportLine> clientProductTotalReportLines = reportService
        .retrieveClientProductTotalReportByTransactionDate(LocalDate.of(2020, 8, 20));

    assertThat(clientProductTotalReportLines.size(), is(0));

    transactionRepository.deleteAll();
  }
}