package com.amro.onesystem.listener.converter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.amro.onesystem.domain.Transaction;
import java.time.LocalDate;
import org.junit.Test;

public class TransactionMessageToTransactionConverterTest {

  @Test
  public void shouldGenerateTransactionFromTransactionMessage() {
    Transaction transaction = new TransactionMessageToTransactionConverter().convert(
        "315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012380     688032000092500000000             O");

    Transaction expectedTransaction = Transaction.builder()
        .id(transaction.getId())
        .recordCode(315)
        .clientType("CL")
        .clientNumber(4321)
        .accountNumber(0002)
        .subAccountNumber(0001)
        .oppositePartyCode("SGXDC")
        .productGroupCode("FU")
        .exchangeCode("SGX")
        .symbol("NK")
        .expirationDate(LocalDate.of(2010, 9, 10))
        .currencyCode("JPY")
        .movementCode("01")
        .buySellCode("B")
        .quantityLongSign("")
        .quantityLong(1L)
        .quantityShortSign("")
        .quantityShort(0L)
        .exchBrokerFeeDec(60.0)
        .ecxhBrokerFeeDC("D")
        .exchBrokerFeeCurCode("USD")
        .clearingFeeDec(30.0)
        .clearingFeeDC("D")
        .clearingFeeCurCode("USD")
        .commission(0.0)
        .commissionDC("D")
        .commissionCurCode("JPY")
        .transactionDate(LocalDate.of(2010, 8, 20))
        .futureReference(1238)
        .ticketNumber("0")
        .externalNumber(688032)
        .transactionPrice(92500000000.0)
        .traderInitials("")
        .oppositeTraderId("")
        .openCloseCode("O")
        .build();
    expectedTransaction.setId(transaction.getId());

    assertThat(transaction, is(expectedTransaction));
  }
}