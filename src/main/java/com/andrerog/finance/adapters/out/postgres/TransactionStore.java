package com.andrerog.finance.adapters.out.postgres;

import com.andrerog.finance.core.FinancialRecord;
import com.andrerog.finance.domain.exceptions.ExceptionCode;
import com.andrerog.finance.domain.exceptions.StoreException;
import com.andrerog.finance.ports.TransactionDataService;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.exception.IntegrityConstraintViolationException;
import org.jooq.generated.tables.BankTransaction;
import org.jooq.generated.tables.records.BankTransactionRecord;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.BatchUpdateException;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionStore implements TransactionDataService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionStore.class);
    private static final String PLSQL_DUPLICATED_RECORD_ERROR_CODE = "23505";

    final DSLContext dataSource;
    public TransactionStore(final DSLContext dataSource) {
        this.dataSource = dataSource;
    }

    public BankTransactionRecord toRecord(FinancialRecord bankTransaction) {
        final BankTransactionRecord bankTransactionRecord = dataSource.newRecord(BankTransaction.BANK_TRANSACTION);
        bankTransactionRecord.setValue(BigDecimal.valueOf(bankTransaction.value()));
        bankTransactionRecord.setDescription(bankTransaction.description());
        bankTransactionRecord.setDate(bankTransaction.date());
        bankTransactionRecord.setBalance(BigDecimal.valueOf(bankTransaction.finalBalance()));
        return bankTransactionRecord;
    }

    public FinancialRecord toModel(BankTransactionRecord record) {
        return new FinancialRecord(
                record.getDate(),
                record.getValue().doubleValue(),
                record.getDescription(),
                record.getBalance().doubleValue()
        );
    }

    @Override
    public FinancialRecord insertTransaction(FinancialRecord transaction) {
        final BankTransactionRecord bankTransactionRecord = toRecord(transaction);
        bankTransactionRecord.changed(BankTransaction.BANK_TRANSACTION.ID, false);
        bankTransactionRecord.store();

        return toModel(bankTransactionRecord);
    }

    @Override
    public void insertTransactions(List<FinancialRecord> transactions) {
        try {
            final List<BankTransactionRecord> transactionRecords = transactions.stream().map(this::toRecord).toList();

            final int[] inserted = dataSource.batchInsert(transactionRecords).execute();

            if (inserted.length != transactions.size()){
                logger.error("Some error occurred with transactions insert");
            }
        } catch (IntegrityConstraintViolationException ex) {
            if (ex.getCause() instanceof BatchUpdateException &&
                    ((PSQLException)ex.getCause().getCause()).getSQLState().equalsIgnoreCase(PLSQL_DUPLICATED_RECORD_ERROR_CODE)){
                logger.error(
                        ExceptionCode.DUPLICATED_RECORD.toString(),
                        ex
                );

                throw new StoreException(
                        ExceptionCode.DUPLICATED_RECORD,
                        ex
                );
            }
        }
    }

    @Override
    public List<FinancialRecord> list() {
        final Result<BankTransactionRecord> fetch = dataSource.selectFrom(BankTransaction.BANK_TRANSACTION).fetch();

       return fetch.stream().map(transaction -> new FinancialRecord(
               transaction.getDate(),
               transaction.getValue().floatValue(),
               transaction.getDescription(),
               transaction.getBalance().floatValue()
       )).collect(Collectors.toList());
    }
}
