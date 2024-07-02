package com.andrerog.finance.ports;

import com.andrerog.finance.core.FinancialRecord;

import java.util.List;

public interface Classifier {

    FinancialRecord classify(FinancialRecord financialRecord);

    FinancialRecord classify(List<FinancialRecord> financialRecords);

}
