package com.amro.onesystem.api.reports;

import com.amro.onesystem.domain.repository.report.ClientProductTotalReportLine;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ReportUtils {

  public static List<ClientProductTotalReportLine> buildClientProductTotalReportLines() {
    return Arrays.asList(
        ClientProductTotalReportLine.builder()
            .accountNumber(1)
            .clientNumber(2)
            .clientType("aClientType")
            .exchangeCode("anExchangeCode")
            .expirationDate(LocalDate.of(2020, 1, 30))
            .productGroupCode("aProductGroupCode")
            .subAccountNumber(3)
            .symbol("aSymbol")
            .total(4L)
            .build(),
        ClientProductTotalReportLine.builder()
            .accountNumber(12)
            .clientNumber(22)
            .clientType("aClientType2")
            .exchangeCode("anExchangeCode2")
            .expirationDate(LocalDate.of(2021, 1, 30))
            .productGroupCode("aProductGroupCode2")
            .subAccountNumber(32)
            .symbol("aSymbol2")
            .total(42L)
            .build()
    );
  }
}
