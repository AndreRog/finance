package com.andrerog.finance.domain.finance;

import com.andrerog.finance.adapters.in.file.SupportedBanks;
import com.andrerog.finance.adapters.in.file.TransactionsReader;
import com.andrerog.finance.core.FinancialRecord;
import com.andrerog.finance.ports.Classifier;
import com.andrerog.finance.ports.TransactionDataService;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UploadTransactions {

    private final Logger LOG = Logger.getLogger(UploadTransactions.class);
    private final TransactionsReader transactionsReader;
    private final TransactionDataService transactionDataService;

    private final Classifier classifier;

    public UploadTransactions(final TransactionsReader transactionsReader,
                              final TransactionDataService transactionDataService,
                              final Classifier classifier) {
        this.transactionsReader = transactionsReader;
        this.transactionDataService = transactionDataService;
        this.classifier = classifier;
    }

    
    public void execute(final SupportedBanks type, final InputStream file) throws IOException {
        final List<FinancialRecord> financialRecords = this.transactionsReader.read(
                file,
                type
        );

        // classify txs
        classifier.classify(financialRecords);

        transactionDataService.insertTransactions(financialRecords);
    }
}
