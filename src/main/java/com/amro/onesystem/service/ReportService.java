package com.amro.onesystem.service;

import com.amro.onesystem.domain.repository.TransactionRepository;
import com.amro.onesystem.domain.repository.report.ClientProductTotalReportLine;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Slf4j
@Service
public class ReportService {

  private final TransactionRepository transactionRepository;

  public ReportService(@NotNull final TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public List<ClientProductTotalReportLine> retrieveClientProductTotalReport() {
    List<ClientProductTotalReportLine> clientProductTotalReportLines = transactionRepository.retrieveClientProductTotalReport();
    return clientProductTotalReportLines;
  }

  public List<ClientProductTotalReportLine> retrieveClientProductTotalReportByTransactionDate(LocalDate transactionDate) {
    List<ClientProductTotalReportLine> clientProductTotalReportLines = transactionRepository
        .retrieveClientProductTotalReportByTransactionDate(transactionDate);
    return clientProductTotalReportLines;
  }
}
