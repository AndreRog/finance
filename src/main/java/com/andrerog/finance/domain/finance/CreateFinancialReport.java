package com.andrerog.finance.domain.finance;

import com.andrerog.finance.adapters.in.file.TransactionsReader;
import com.andrerog.finance.core.FinancialRecord;
import com.andrerog.finance.core.FinancialSummary;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CreateFinancialReport {

    private final TransactionsReader transactionsReader;

    public CreateFinancialReport(TransactionsReader transactionsReader) {
        this.transactionsReader = transactionsReader;
    }

    public FinancialSummary execute(CreateFinancialRecordsRequest createFinancialRecord) throws IOException {
        final List<FinancialRecord> financialRecords = this.transactionsReader.read(
                createFinancialRecord.file(),
                createFinancialRecord.type()
        );

        final double totalMoneySpent = financialRecords.stream()
                .map(FinancialRecord::value)
                .reduce(0.0, Double::sum);

        final Optional<FinancialRecord> max =
                financialRecords.stream()
                        .max(Comparator.comparing(FinancialRecord::date));
        final Optional<FinancialRecord> min =
                financialRecords.stream()
                        .min(Comparator.comparing(FinancialRecord::date));

        return new FinancialSummary(
                max.get().finalBalance() - min.get().finalBalance(), // this names what is this lol
                max.get().finalBalance()
        );

    }
}
