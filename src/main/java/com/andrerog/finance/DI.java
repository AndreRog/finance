package com.andrerog.finance;

import com.andrerog.finance.adapters.in.file.ExcelReader;
import com.andrerog.finance.adapters.in.file.TransactionsReader;
import com.andrerog.finance.adapters.out.postgres.TransactionStore;
import com.andrerog.finance.domain.finance.CreateFinancialReport;
import com.andrerog.finance.domain.finance.ListBankTransactions;
import com.andrerog.finance.domain.finance.UploadTransactions;
import com.andrerog.finance.ports.TransactionDataService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import org.jooq.DSLContext;

public class DI {

    @Inject
    DSLContext dataSource;

    @ApplicationScoped
    @Produces
    TransactionsReader transactionsReader() {
        return new ExcelReader();
    }
    @ApplicationScoped
    @Produces
    CreateFinancialReport createFinancialReport(final TransactionsReader transactionsReader) {
        return new CreateFinancialReport(transactionsReader);
    }

    @ApplicationScoped
    @Produces
    TransactionDataService transactionDataService(final DSLContext dataSource){
        return new TransactionStore(dataSource);
    }

    @ApplicationScoped
    @Produces
    UploadTransactions uploadTransactions(final TransactionsReader transactionsReader,
                                          final TransactionDataService transactionDataService) {
        return new UploadTransactions(transactionsReader, transactionDataService);
    }

    @ApplicationScoped
    @Produces
    ListBankTransactions listBankTransactions(final TransactionDataService transactionDataService) {
        return new ListBankTransactions(transactionDataService);
    }
}
