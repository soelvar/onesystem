package com.amro.onesystem.api.reports.converter;

import com.amro.onesystem.api.reports.model.response.ClientInformationModel;
import com.amro.onesystem.api.reports.model.response.ProductInformationModel;
import com.amro.onesystem.api.reports.model.response.ReportItemModel;
import com.amro.onesystem.api.reports.model.response.ReportModel;
import com.amro.onesystem.domain.repository.report.ClientProductTotalReportLine;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClientProductTotalReportLinesToReportModelConverter implements Converter<List<ClientProductTotalReportLine>, ReportModel> {

  @Override
  public ReportModel convert(final List<ClientProductTotalReportLine> clientProductTotalReportLines) {
    return ReportModel.builder()
        .reportItemModelList(clientProductTotalReportLines.stream().map(clientProductTotalReportLine -> {
          return ReportItemModel.builder()
              .clientInformationModel(ClientInformationModel.builder()
                  .clientType(clientProductTotalReportLine.getClientType())
                  .clientNumber(clientProductTotalReportLine.getClientNumber())
                  .accountNumber(clientProductTotalReportLine.getAccountNumber())
                  .subAccountNumber(clientProductTotalReportLine.getSubAccountNumber())
                  .build())
              .productInformationModel(ProductInformationModel.builder()
                  .exchangeCode(clientProductTotalReportLine.getExchangeCode())
                  .expirationDate(clientProductTotalReportLine.getExpirationDate())
                  .productGroupCode(clientProductTotalReportLine.getProductGroupCode())
                  .symbol(clientProductTotalReportLine.getSymbol())
                  .build())
              .total(clientProductTotalReportLine.getTotal())
              .build();
        }).collect(Collectors.toList()))
        .build();
  }
}
