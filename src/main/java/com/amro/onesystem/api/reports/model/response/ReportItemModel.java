package com.amro.onesystem.api.reports.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReportItemModel {

  private ClientInformationModel clientInformationModel;
  private ProductInformationModel productInformationModel;
  private Long total;

}
