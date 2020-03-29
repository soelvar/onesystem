package com.amro.onesystem.api.reports;

import static com.amro.onesystem.api.reports.ReportUtils.buildClientProductTotalReportLines;
import static com.amro.onesystem.api.reports.ReportsController.ONESYSTEM_REPORTS_URI;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.amro.onesystem.ApplicationWithoutKafkaIT;
import com.amro.onesystem.TestUtils;
import com.amro.onesystem.domain.repository.TransactionRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.MvcResult;

public class ReportsControllerIT extends ApplicationWithoutKafkaIT {

  @MockBean
  private TransactionRepository transactionRepository;

  @Value("classpath:files/report-model.json")
  private Resource reportModelJson;

  @Test
  public void should_return_httpStatus_200_and_validate_json() throws Exception {

    when(transactionRepository.retrieveClientProductTotalReport()).thenReturn(buildClientProductTotalReportLines());

    MvcResult mvcResult = mockMvc
        .perform(get(ONESYSTEM_REPORTS_URI)
            .accept(APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();

    JSONAssert.assertEquals(TestUtils.toString(reportModelJson), mvcResult.getResponse().getContentAsString(), JSONCompareMode.STRICT);

    verify(transactionRepository, times(1)).retrieveClientProductTotalReport();
  }

  @Test
  public void should_return_httpStatus_200_and_validate_csv() throws Exception {

    when(transactionRepository.retrieveClientProductTotalReport()).thenReturn(buildClientProductTotalReportLines());

    MvcResult mvcResult = mockMvc
        .perform(get(ONESYSTEM_REPORTS_URI)
            .accept("text/csv"))
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();

    assertThat(mvcResult.getResponse().getContentAsString(),
        is("aClientType 2 1 1,anExchangeCode aProductGroupCode aSymbol 2020-01-30,4\naClientType2 22 12 12,anExchangeCode2 aProductGroupCode2 aSymbol2 2021-01-30,42"));

    verify(transactionRepository, times(1)).retrieveClientProductTotalReport();
  }

  @Test
  public void should_return_httpStatus_200_and_validate_json_when_query_by_transaction_date() throws Exception {
    LocalDate localDate = LocalDate.of(2020, 12, 30);

    when(transactionRepository.retrieveClientProductTotalReportByTransactionDate(localDate)).thenReturn(buildClientProductTotalReportLines());

    MvcResult mvcResult = mockMvc
        .perform(get(ONESYSTEM_REPORTS_URI)
            .accept(APPLICATION_JSON_VALUE)
            .param("transactionDate", "2020-12-30")
        )
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();

    JSONAssert.assertEquals(TestUtils.toString(reportModelJson), mvcResult.getResponse().getContentAsString(), JSONCompareMode.STRICT);

    verify(transactionRepository, times(1)).retrieveClientProductTotalReportByTransactionDate(localDate);
  }

  @Test
  public void should_return_httpStatus_200_and_validate_csv_when_query_by_transaction_date() throws Exception {
    LocalDate localDate = LocalDate.of(2020, 12, 30);

    when(transactionRepository.retrieveClientProductTotalReportByTransactionDate(localDate)).thenReturn(buildClientProductTotalReportLines());

    MvcResult mvcResult = mockMvc
        .perform(get(ONESYSTEM_REPORTS_URI)
            .accept("text/csv")
            .param("transactionDate", "2020-12-30")
        )
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn();

    assertThat(mvcResult.getResponse().getContentAsString(),
        is("aClientType 2 1 1,anExchangeCode aProductGroupCode aSymbol 2020-01-30,4\naClientType2 22 12 12,anExchangeCode2 aProductGroupCode2 aSymbol2 2021-01-30,42"));

    verify(transactionRepository, times(1)).retrieveClientProductTotalReportByTransactionDate(localDate);
  }

  @Test
  public void should_return_httpStatus_404_and_validate_json_when_no_transactions_found() throws Exception {

    when(transactionRepository.retrieveClientProductTotalReport()).thenReturn(new ArrayList<>());

    MvcResult mvcResult = mockMvc
        .perform(get(ONESYSTEM_REPORTS_URI)
            .accept(APPLICATION_JSON_VALUE)
        )
        .andExpect(status().isNotFound())
        .andDo(print())
        .andReturn();

    verify(transactionRepository, times(1)).retrieveClientProductTotalReport();
  }

  @Test
  public void should_return_httpStatus_404_and_validate_csv_when_no_transactions_found() throws Exception {

    when(transactionRepository.retrieveClientProductTotalReport()).thenReturn(new ArrayList<>());

    MvcResult mvcResult = mockMvc
        .perform(get(ONESYSTEM_REPORTS_URI)
            .accept("text/csv")
        )
        .andExpect(status().isNotFound())
        .andDo(print())
        .andReturn();

    verify(transactionRepository, times(1)).retrieveClientProductTotalReport();
  }

  @Test
  public void should_return_httpStatus_500_when_json_and_illegalstate() throws Exception {

    when(transactionRepository.retrieveClientProductTotalReport()).thenThrow(new IllegalStateException("test"));

    MvcResult mvcResult = mockMvc
        .perform(get(ONESYSTEM_REPORTS_URI)
            .accept(APPLICATION_JSON_VALUE)
        )
        .andExpect(status().is5xxServerError())
        .andDo(print())
        .andReturn();

    verify(transactionRepository, times(1)).retrieveClientProductTotalReport();
  }

  @Test
  public void should_return_httpStatus_500_when_csv_and_illegalstate() throws Exception {

    when(transactionRepository.retrieveClientProductTotalReport()).thenThrow(new IllegalStateException("test"));

    MvcResult mvcResult = mockMvc
        .perform(get(ONESYSTEM_REPORTS_URI)
            .accept("text/csv")
        )
        .andExpect(status().is5xxServerError())
        .andDo(print())
        .andReturn();

    verify(transactionRepository, times(1)).retrieveClientProductTotalReport();
  }
}