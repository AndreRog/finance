package com.andrerog.finance.adapters.out.txClassifier;

import com.andrerog.finance.core.FinancialRecord;
import com.andrerog.finance.ports.Classifier;

import java.util.List;

public class TransactionClassifier implements Classifier {
    @Override
    public FinancialRecord classify(FinancialRecord financialRecord) {
        return null;
    }

    @Override
    public FinancialRecord classify(List<FinancialRecord> financialRecords) {
        return null;
    }
}
