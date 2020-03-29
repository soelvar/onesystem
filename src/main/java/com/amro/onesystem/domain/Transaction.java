package com.amro.onesystem.domain;

import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@javax.persistence.Entity
@Table(name = "TRANSACTION")
public class Transaction {

  @Default
  @NotNull
  @Id
  @Column
  private String id = UUID.randomUUID().toString();

  @NotNull
  @Min(0)
  @Max(999)
  @Column
  private Integer recordCode;

  @NotNull
  @Size(min = 0, max = 4)
  @Column
  private String clientType;

  @NotNull
  @Min(0)
  @Max(9999)
  @Column
  private Integer clientNumber;

  @NotNull
  @Min(0)
  @Max(9999)
  @Column
  private Integer accountNumber;

  @NotNull
  @Min(0)
  @Max(9999)
  @Column
  private Integer subAccountNumber;

  @NotNull
  @Size(min = 0, max = 6)
  @Column
  private String oppositePartyCode;

  @NotNull
  @Size(min = 0, max = 2)
  @Column
  private String productGroupCode;

  @NotNull
  @Size(min = 0, max = 4)
  @Column
  private String exchangeCode;

  @NotNull
  @Size(min = 0, max = 6)
  @Column
  private String symbol;

  @NotNull
  @Column
  private LocalDate expirationDate;

  @NotNull
  @Size(min = 0, max = 3)
  @Column
  private String currencyCode;

  @NotNull
  @Size(min = 0, max = 2)
  @Column
  private String movementCode;

  @NotNull
  @Size(min = 0, max = 1)
  @Column
  private String buySellCode;

  @NotNull
  @Size(min = 0, max = 1)
  @Column
  private String quantityLongSign;

  @NotNull
  @Min(0L)
  @Max(9999999999L)
  @Column
  private Long quantityLong;

  @NotNull
  @Size(min = 0, max = 1)
  @Column
  private String quantityShortSign;

  @NotNull
  @Min(0L)
  @Max(9999999999L)
  @Column
  private Long quantityShort;

  @NotNull
  @Column
  private Double exchBrokerFeeDec;

  @NotNull
  @Size(min = 0, max = 1)
  @Column
  private String ecxhBrokerFeeDC;

  @NotNull
  @Size(min = 0, max = 3)
  @Column
  private String exchBrokerFeeCurCode;

  @NotNull
  @Column
  private Double clearingFeeDec;

  @NotNull
  @Size(min = 0, max = 1)
  @Column
  private String clearingFeeDC;

  @NotNull
  @Size(min = 0, max = 3)
  @Column
  private String clearingFeeCurCode;

  @NotNull
  @Column
  private Double commission;

  @NotNull
  @Size(min = 0, max = 1)
  @Column
  private String commissionDC;

  @NotNull
  @Size(min = 0, max = 3)
  @Column
  private String commissionCurCode;

  @NotNull
  private LocalDate transactionDate;

  @NotNull
  @Min(0)
  @Max(999999)
  @Column
  private Integer futureReference;

  @NotNull
  @Size(min = 0, max = 6)
  @Column
  private String ticketNumber;

  @NotNull
  @Min(0)
  @Max(999999)
  @Column
  private Integer externalNumber;

  @NotNull
  @Column
  private Double transactionPrice;

  @NotNull
  @Size(min = 0, max = 6)
  @Column
  private String traderInitials;

  @NotNull
  @Size(min = 0, max = 7)
  @Column
  private String oppositeTraderId;

  @NotNull
  @Size(min = 0, max = 1)
  @Column
  private String openCloseCode;
}

