package com.andrerog.finance.core;


import java.time.OffsetDateTime;

public record FinancialRecord(OffsetDateTime date, double value, String description, double finalBalance) {
}
