package com.amro.onesystem.api.inputs;

import static com.amro.onesystem.api.inputs.InputsController.ONESYSTEM_INPUTS_URI;
import static com.amro.onesystem.api.reports.ReportUtils.buildClientProductTotalReportLines;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.amro.onesystem.ApplicationWithoutKafkaIT;
import com.amro.onesystem.api.reports.ReportsController;
import com.amro.onesystem.api.reports.converter.ClientProductTotalReportLinesToCSVConverter;
import com.amro.onesystem.api.reports.converter.ClientProductTotalReportLinesToReportModelConverter;
import com.amro.onesystem.service.ReportService;
import java.time.LocalDate;
import java.util.Arrays;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
public class InputsControllerTest extends ApplicationWithoutKafkaIT {

  private ReportsController controller;

  @Mock
  private ReportService reportService;

  @Autowired
  private ClientProductTotalReportLinesToCSVConverter clientProductTotalReportLinesToCSVConverter;

  @Autowired
  private ClientProductTotalReportLinesToReportModelConverter clientProductTotalReportLinesToReportModelConverter;

  @Before
  public void setup() {
    controller = new ReportsController(reportService, clientProductTotalReportLinesToCSVConverter, clientProductTotalReportLinesToReportModelConverter);
  }

  @Test
  public void should_retrieve_csv_report_when_no_transaction_date() {
    when(reportService.retrieveClientProductTotalReport()).thenReturn(buildClientProductTotalReportLines());

    ResponseEntity<String> report = controller.getReportA(null);

    System.out.println("report = " + report);
    assertThat(report.getBody(), is("aClientType 2 1 1,anExchangeCode aProductGroupCode aSymbol 2020-01-30,4\n"
        + "aClientType2 22 12 12,anExchangeCode2 aProductGroupCode2 aSymbol2 2021-01-30,42"));

    verify(reportService, times(1)).retrieveClientProductTotalReport();
  }

  @Test
  public void should_retrieve_csv_report_when_transaction_date() {
    when(reportService.retrieveClientProductTotalReportByTransactionDate(LocalDate.of(2020, 1,1))).thenReturn(buildClientProductTotalReportLines());

    ResponseEntity<String> report = controller.getReportA(LocalDate.of(2020, 1,1));

    System.out.println("report = " + report);
    assertThat(report.getBody(), is("aClientType 2 1 1,anExchangeCode aProductGroupCode aSymbol 2020-01-30,4\n"
        + "aClientType2 22 12 12,anExchangeCode2 aProductGroupCode2 aSymbol2 2021-01-30,42"));

    verify(reportService, times(1)).retrieveClientProductTotalReportByTransactionDate(LocalDate.of(2020, 1,1));
  }

}