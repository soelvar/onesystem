package com.amro.onesystem.domain.repository;

import com.amro.onesystem.domain.Transaction;
import com.amro.onesystem.domain.repository.report.ClientProductTotalReportLine;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String> {

  @Query(
      value = ""
          + " SELECT new com.amro.onesystem.domain.repository.report.ClientProductTotalReportLine( "
          + "     t.clientType, "
          + "     t.clientNumber, "
          + "     t.accountNumber, "
          + "     t.subAccountNumber, "
          + "     t.exchangeCode, "
          + "     t.productGroupCode, "
          + "     t.symbol, "
          + "     t.expirationDate, "
          + "     sum(t.quantityLong -t.quantityShort)"
          + " ) "
          + " FROM Transaction t "
          + " GROUP BY "
          + "     t.clientType, "
          + "     t.clientNumber, "
          + "     t.accountNumber, "
          + "     t.subAccountNumber, "
          + "     t.exchangeCode, "
          + "     t.productGroupCode, "
          + "     t.symbol, "
          + "     t.expirationDate "
  )
  List<ClientProductTotalReportLine> retrieveClientProductTotalReport();

  @Query(
      value = ""
          + " SELECT new com.amro.onesystem.domain.repository.report.ClientProductTotalReportLine( "
          + "     t.clientType, "
          + "     t.clientNumber, "
          + "     t.accountNumber, "
          + "     t.subAccountNumber, "
          + "     t.exchangeCode, "
          + "     t.productGroupCode, "
          + "     t.symbol, "
          + "     t.expirationDate, "
          + "     sum(t.quantityLong -t.quantityShort)"
          + " ) "
          + " FROM Transaction t "
          + " WHERE t.transactionDate = :transactionDate "
          + " GROUP BY "
          + "     t.clientType, "
          + "     t.clientNumber, "
          + "     t.accountNumber, "
          + "     t.subAccountNumber, "
          + "     t.exchangeCode, "
          + "     t.productGroupCode, "
          + "     t.symbol, "
          + "     t.expirationDate "
  )
  List<ClientProductTotalReportLine> retrieveClientProductTotalReportByTransactionDate(@Param("transactionDate") LocalDate transactionDate);
}
