package com.andrerog.finance.ports;

import com.andrerog.finance.core.FinancialRecord;

import java.util.List;

public interface TransactionDataService {

    FinancialRecord insertTransaction(final FinancialRecord transaction);

    void insertTransactions(final List<FinancialRecord> transactions);
}
