package com.amro.onesystem.api.reports.model.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReportModel {

  private List<ReportItemModel> reportItemModelList;

}
