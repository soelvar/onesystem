package com.amro.onesystem.api.reports.model.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductInformationModel {

  private String exchangeCode;

  private String productGroupCode;

  private String symbol;

  private LocalDate expirationDate;
}
