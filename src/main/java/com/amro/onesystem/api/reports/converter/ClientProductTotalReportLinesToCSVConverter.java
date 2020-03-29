package com.amro.onesystem.api.reports.converter;

import com.amro.onesystem.domain.repository.report.ClientProductTotalReportLine;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClientProductTotalReportLinesToCSVConverter implements Converter<List<ClientProductTotalReportLine>, String> {

  @Override
  public String convert(final List<ClientProductTotalReportLine> clientProductTotalReportLines) {
    Optional<String> csvLinesOptional = clientProductTotalReportLines.stream().map(clientProductTotalReportLine -> {
      StringBuilder sb = new StringBuilder();

      sb.append(clientProductTotalReportLine.getClientType());
      sb.append(" ");
      sb.append(clientProductTotalReportLine.getClientNumber());
      sb.append(" ");
      sb.append(clientProductTotalReportLine.getAccountNumber());
      sb.append(" ");
      sb.append(clientProductTotalReportLine.getAccountNumber());
      sb.append(",");
      sb.append(clientProductTotalReportLine.getExchangeCode());
      sb.append(" ");
      sb.append(clientProductTotalReportLine.getProductGroupCode());
      sb.append(" ");
      sb.append(clientProductTotalReportLine.getSymbol());
      sb.append(" ");
      sb.append(clientProductTotalReportLine.getExpirationDate());
      sb.append(",");
      sb.append(clientProductTotalReportLine.getTotal());

      return sb.toString();
    }).collect(Collectors.toList()).stream().reduce((s1, s2) -> s1 + "\n" + s2);

    if (csvLinesOptional.isPresent()) {
      return csvLinesOptional.get();
    }
    return null;
  }
}
