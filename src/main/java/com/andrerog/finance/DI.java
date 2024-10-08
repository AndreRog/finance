package com.andrerog.finance;

import com.andrerog.finance.adapters.in.file.ExcelReader;
import com.andrerog.finance.adapters.in.file.TransactionsReader;
import com.andrerog.finance.adapters.out.postgres.TransactionStore;
import com.andrerog.finance.adapters.out.txClassifier.TransactionClassifier;
import com.andrerog.finance.domain.bank.ListBankTypes;
import com.andrerog.finance.domain.finance.ListBankTransactions;
import com.andrerog.finance.domain.finance.UploadTransactions;
import com.andrerog.finance.ports.Classifier;
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
    TransactionDataService transactionDataService(final DSLContext dataSource){
        return new TransactionStore(dataSource);
    }

    @ApplicationScoped
    @Produces
    Classifier TransactionClassifier() {
        return new TransactionClassifier();
    }

    @ApplicationScoped
    @Produces
    UploadTransactions uploadTransactions(final TransactionsReader transactionsReader,
                                          final TransactionDataService transactionDataService,
                                          final Classifier classifier) {
        return new UploadTransactions(transactionsReader, transactionDataService, classifier);
    }

    @ApplicationScoped
    @Produces
    ListBankTransactions listBankTransactions(final TransactionDataService transactionDataService) {
        return new ListBankTransactions(transactionDataService);
    }

    @ApplicationScoped
    @Produces
    ListBankTypes listBankTypes() {
        return new ListBankTypes(); // TODO: should receive DB
    }
}
