package com.amro.onesystem.api.reports;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.amro.onesystem.api.reports.converter.ClientProductTotalReportLinesToCSVConverter;
import com.amro.onesystem.domain.repository.report.ClientProductTotalReportLine;
import java.time.LocalDate;
import java.util.Arrays;
import org.junit.Test;

public class ClientProductTotalReportLinesToCSVConverterTest {

  @Test
  public void should() {
    ClientProductTotalReportLinesToCSVConverter clientProductTotalReportLinesToCSVConverter = new ClientProductTotalReportLinesToCSVConverter();
    String csv = clientProductTotalReportLinesToCSVConverter.convert(Arrays.asList(
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
        )
    );

    assertThat(csv,
        is("aClientType 2 1 1,anExchangeCode aProductGroupCode aSymbol 2020-01-30,4\naClientType2 22 12 12,anExchangeCode2 aProductGroupCode2 aSymbol2 2021-01-30,42"));
  }
}