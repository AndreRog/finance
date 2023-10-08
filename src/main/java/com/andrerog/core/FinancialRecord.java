package com.andrerog.core;

import java.util.Date;

public record FinancialRecord(Date date, double value, String description, double finalBalance) {
}
