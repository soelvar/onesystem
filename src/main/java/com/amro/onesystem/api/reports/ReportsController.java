package com.amro.onesystem.api.reports;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.amro.onesystem.api.reports.converter.ClientProductTotalReportLinesToCSVConverter;
import com.amro.onesystem.api.reports.converter.ClientProductTotalReportLinesToReportModelConverter;
import com.amro.onesystem.api.reports.model.response.ReportModel;
import com.amro.onesystem.domain.repository.report.ClientProductTotalReportLine;
import com.amro.onesystem.service.ReportService;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Validated
@RestController
@RequestMapping(value = ReportsController.ONESYSTEM_REPORTS_URI)
public class ReportsController {

  public static final String ONESYSTEM_REPORTS_URI = "/onesystem/reports";
  private final ReportService reportService;

  private final ClientProductTotalReportLinesToCSVConverter clientProductTotalReportLinesToCSVConverter;
  private final ClientProductTotalReportLinesToReportModelConverter clientProductTotalReportLinesToReportModelConverter;

  public ReportsController(
      @NotNull final ReportService reportService,
      @NotNull final ClientProductTotalReportLinesToCSVConverter clientProductTotalReportLinesToCSVConverter,
      @NotNull final ClientProductTotalReportLinesToReportModelConverter clientProductTotalReportLinesToReportModelConverter) {
    this.reportService = reportService;
    this.clientProductTotalReportLinesToCSVConverter = clientProductTotalReportLinesToCSVConverter;
    this.clientProductTotalReportLinesToReportModelConverter = clientProductTotalReportLinesToReportModelConverter;
  }

  @GetMapping(produces = "text/csv")
  public ResponseEntity<String> getReportA(@RequestParam(value = "transactionDate", required = false)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate transactionDate) {
    String csv = null;
    try {
      List<ClientProductTotalReportLine> clientProductTotalReportLines = retrieveTransations(transactionDate);
      csv = clientProductTotalReportLinesToCSVConverter.convert(clientProductTotalReportLines);
    } catch (Exception e) {
      log.error("Unable to retrieve report!", e);
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve report!");
    }

    if (csv == null) {
      return ResponseEntity.status(NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(csv);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public ReportModel getReportB(
      @RequestParam(required = false)
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate transactionDate) {
    ReportModel reportModel = null;
    try {
      List<ClientProductTotalReportLine> clientProductTotalReportLines = retrieveTransations(transactionDate);
      reportModel = clientProductTotalReportLinesToReportModelConverter.convert(clientProductTotalReportLines);
    } catch (Exception e) {
      log.error("Unable to retrieve report!", e);
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve report!");
    }

    if (CollectionUtils.isEmpty(reportModel.getReportItemModelList())) {
      throw new ResponseStatusException(NOT_FOUND, "Unable to retrieve report!");
    }
    return reportModel;
  }


  private List<ClientProductTotalReportLine> retrieveTransations(
      @RequestParam(required = false)
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate transactionDate) {
    List<ClientProductTotalReportLine> clientProductTotalReportLines;
    if (transactionDate != null) {
      clientProductTotalReportLines = reportService.retrieveClientProductTotalReportByTransactionDate(transactionDate);
    } else {
      clientProductTotalReportLines = reportService.retrieveClientProductTotalReport();
    }
    return clientProductTotalReportLines;
  }
}
