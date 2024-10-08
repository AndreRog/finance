package com.andrerog.finance.core;


import java.time.OffsetDateTime;

public record FinancialRecord(OffsetDateTime date, double value, String description, double finalBalance, String category) {
    public FinancialRecord(OffsetDateTime date, double value, String description, double finalBalance) {
        this(date, value, description, finalBalance, null);
    }
}
