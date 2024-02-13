package com.andrerog.finance.domain.finance;

import com.andrerog.finance.core.FinancialRecord;
import com.andrerog.finance.ports.TransactionDataService;
import org.jboss.logging.Logger;

import java.util.List;

public class ListBankTransactions {

    private final Logger LOG = Logger.getLogger(UploadTransactions.class);
    private final TransactionDataService transactionDataService;

    public ListBankTransactions(final TransactionDataService transactionDataService) {
        this.transactionDataService = transactionDataService;
    }

    public List<FinancialRecord> execute(){
        return this.transactionDataService.list();
    }
}
