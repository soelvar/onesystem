package com.amro.onesystem.domain.repository.report;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClientProductTotalReportLine {

  public ClientProductTotalReportLine(String clientType, Integer clientNumber, Integer accountNumber, Integer subAccountNumber,
      String exchangeCode, String productGroupCode, String symbol, LocalDate expirationDate, Long total) {
    this.clientType = clientType;
    this.clientNumber = clientNumber;
    this.accountNumber = accountNumber;
    this.subAccountNumber = subAccountNumber;
    this.exchangeCode = exchangeCode;
    this.productGroupCode = productGroupCode;
    this.symbol = symbol;
    this.expirationDate = expirationDate;
    this.total = total;
  }

  private String clientType;

  private Integer clientNumber;

  private Integer accountNumber;

  private Integer subAccountNumber;

  private String exchangeCode;

  private String productGroupCode;

  private String symbol;

  private LocalDate expirationDate;

  private Long total;

}
