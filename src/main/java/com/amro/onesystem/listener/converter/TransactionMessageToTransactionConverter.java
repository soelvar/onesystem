package com.amro.onesystem.listener.converter;

import com.amro.onesystem.domain.Transaction;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TransactionMessageToTransactionConverter implements Converter<String, Transaction> {

  @Override
  public Transaction convert(String transactionMessage) {
    Transaction transaction = new Transaction();
    transaction.setRecordCode(Integer.valueOf(transactionMessage.substring(0, 3).stripTrailing()));
    transaction.setClientType(transactionMessage.substring(3, 7).stripTrailing());
    transaction.setClientNumber(Integer.valueOf(transactionMessage.substring(7, 11).stripTrailing()));
    transaction.setAccountNumber(Integer.valueOf(transactionMessage.substring(11, 15).stripTrailing()));
    transaction.setSubAccountNumber(Integer.valueOf(transactionMessage.substring(15, 19).stripTrailing()));
    transaction.setOppositePartyCode(transactionMessage.substring(19, 25).stripTrailing());
    transaction.setProductGroupCode(transactionMessage.substring(25, 27).stripTrailing());
    transaction.setExchangeCode(transactionMessage.substring(27, 31).stripTrailing());
    transaction.setSymbol(transactionMessage.substring(31, 37).stripTrailing());
    transaction.setExpirationDate(LocalDate.parse(transactionMessage.substring(37, 45).stripTrailing(), DateTimeFormatter.ofPattern("yyyyMMdd")));
    transaction.setCurrencyCode(transactionMessage.substring(45, 48).stripTrailing());
    transaction.setMovementCode(transactionMessage.substring(48, 50).stripTrailing());
    transaction.setBuySellCode(transactionMessage.substring(50, 51).stripTrailing());
    transaction.setQuantityLongSign(transactionMessage.substring(51, 52).stripTrailing());
    transaction.setQuantityLong(Long.valueOf(transactionMessage.substring(52, 62).stripTrailing()));
    transaction.setQuantityShortSign(transactionMessage.substring(62, 63).stripTrailing());
    transaction.setQuantityShort(Long.valueOf(transactionMessage.substring(63, 73).stripTrailing()));
    transaction.setExchBrokerFeeDec(Double.valueOf(transactionMessage.substring(73, 85).stripTrailing()));
    transaction.setEcxhBrokerFeeDC(transactionMessage.substring(85, 86).stripTrailing());
    transaction.setExchBrokerFeeCurCode(transactionMessage.substring(86, 89).stripTrailing());
    transaction.setClearingFeeDec(Double.valueOf(transactionMessage.substring(89, 101).stripTrailing()));
    transaction.setClearingFeeDC(transactionMessage.substring(101, 102).stripTrailing());
    transaction.setClearingFeeCurCode(transactionMessage.substring(102, 105).stripTrailing());
    transaction.setCommission(Double.valueOf(transactionMessage.substring(105, 117).stripTrailing()));
    transaction.setCommissionDC(transactionMessage.substring(117, 118).stripTrailing());
    transaction.setCommissionCurCode(transactionMessage.substring(118, 121).stripTrailing());
    transaction.setTransactionDate(LocalDate.parse(transactionMessage.substring(121, 129).stripTrailing(), DateTimeFormatter.ofPattern("yyyyMMdd")));
    transaction.setFutureReference(Integer.valueOf(transactionMessage.substring(129, 135).stripTrailing()));
    transaction.setTicketNumber(transactionMessage.substring(135, 141).stripTrailing());
    transaction.setExternalNumber(Integer.valueOf(transactionMessage.substring(141, 147).stripTrailing()));
    transaction.setTransactionPrice(Double.valueOf(transactionMessage.substring(147, 162).stripTrailing()));
    transaction.setTraderInitials(transactionMessage.substring(162, 168).stripTrailing());
    transaction.setOppositeTraderId(transactionMessage.substring(168, 175).stripTrailing());
    transaction.setOpenCloseCode(transactionMessage.substring(175, 176).stripTrailing());

    return transaction;
  }
}
