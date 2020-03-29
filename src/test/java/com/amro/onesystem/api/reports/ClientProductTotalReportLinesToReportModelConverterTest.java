package com.amro.onesystem.api.reports;

import static com.amro.onesystem.api.reports.ReportUtils.buildClientProductTotalReportLines;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.amro.onesystem.api.reports.converter.ClientProductTotalReportLinesToReportModelConverter;
import com.amro.onesystem.api.reports.model.response.ReportModel;
import java.time.LocalDate;
import org.junit.Test;

public class ClientProductTotalReportLinesToReportModelConverterTest {

  @Test
  public void should() {
    ClientProductTotalReportLinesToReportModelConverter clientProductTotalReportLinesToReportModelConverter = new ClientProductTotalReportLinesToReportModelConverter();
    ReportModel reportModel = clientProductTotalReportLinesToReportModelConverter.convert(buildClientProductTotalReportLines());

    assertThat(reportModel.getReportItemModelList().size(), is(2));

    assertThat(reportModel.getReportItemModelList().get(0).getClientInformationModel().getClientType(), is("aClientType"));
    assertThat(reportModel.getReportItemModelList().get(0).getClientInformationModel().getClientNumber(), is(2));
    assertThat(reportModel.getReportItemModelList().get(0).getClientInformationModel().getAccountNumber(), is(1));
    assertThat(reportModel.getReportItemModelList().get(0).getClientInformationModel().getSubAccountNumber(), is(3));
    assertThat(reportModel.getReportItemModelList().get(0).getProductInformationModel().getExchangeCode(), is("anExchangeCode"));
    assertThat(reportModel.getReportItemModelList().get(0).getProductInformationModel().getExpirationDate(), is(LocalDate.of(2020, 1, 30)));
    assertThat(reportModel.getReportItemModelList().get(0).getProductInformationModel().getProductGroupCode(), is("aProductGroupCode"));
    assertThat(reportModel.getReportItemModelList().get(0).getProductInformationModel().getSymbol(), is("aSymbol"));
    assertThat(reportModel.getReportItemModelList().get(0).getTotal(), is(4L));

    assertThat(reportModel.getReportItemModelList().get(1).getClientInformationModel().getClientType(), is("aClientType2"));
    assertThat(reportModel.getReportItemModelList().get(1).getClientInformationModel().getClientNumber(), is(22));
    assertThat(reportModel.getReportItemModelList().get(1).getClientInformationModel().getAccountNumber(), is(12));
    assertThat(reportModel.getReportItemModelList().get(1).getClientInformationModel().getSubAccountNumber(), is(32));
    assertThat(reportModel.getReportItemModelList().get(1).getProductInformationModel().getExchangeCode(), is("anExchangeCode2"));
    assertThat(reportModel.getReportItemModelList().get(1).getProductInformationModel().getExpirationDate(), is(LocalDate.of(2021, 1, 30)));
    assertThat(reportModel.getReportItemModelList().get(1).getProductInformationModel().getProductGroupCode(), is("aProductGroupCode2"));
    assertThat(reportModel.getReportItemModelList().get(1).getProductInformationModel().getSymbol(), is("aSymbol2"));
    assertThat(reportModel.getReportItemModelList().get(1).getTotal(), is(42L));

  }

}