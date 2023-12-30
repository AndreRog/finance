package com.andrerog.finance;

import com.andrerog.finance.adapters.in.file.ExcelReader;
import com.andrerog.finance.adapters.in.file.TransactionsReader;
import com.andrerog.finance.domain.finance.CreateFinancialReport;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;

public class DI {



    @ApplicationScoped
    @Produces
    TransactionsReader transactionsReader() {
        return new ExcelReader();
    }
    @ApplicationScoped
    @Produces
    CreateFinancialReport createFinancialReport(TransactionsReader transactionsReader) {
        return new CreateFinancialReport(transactionsReader);
    }

}
