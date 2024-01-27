package com.andrerog.finance.domain.finance;

import com.andrerog.finance.adapters.in.file.SupportedBanks;
import com.andrerog.finance.adapters.in.file.TransactionsReader;
import com.andrerog.finance.core.FinancialRecord;
import com.andrerog.finance.ports.TransactionDataService;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UploadTransactions {

    private final TransactionsReader transactionsReader;
    private final TransactionDataService transactionDataService;
    private final Logger LOG = Logger.getLogger(UploadTransactions.class);

    public UploadTransactions(final TransactionsReader transactionsReader,
                              final TransactionDataService transactionDataService) {
        this.transactionsReader = transactionsReader;
        this.transactionDataService = transactionDataService;
    }

    
    public void execute(final SupportedBanks type, final InputStream file) throws IOException {
        final List<FinancialRecord> financialRecords = this.transactionsReader.read(
                file,
                type
        );

        transactionDataService.insertTransactions(financialRecords);
    }
}
