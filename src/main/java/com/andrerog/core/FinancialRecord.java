package com.andrerog.core;

import java.util.Date;

public record FinancialRecord(Date date, float value, String description, float finalBalance) {
}
