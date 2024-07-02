package com.andrerog.finance.core;


import java.time.OffsetDateTime;
import java.util.Objects;

public final class FinancialRecord {
    private final OffsetDateTime date;
    private final double value;
    private final String description;
    private final double finalBalance;
    private String category;

    public FinancialRecord(OffsetDateTime date, double value, String description, double finalBalance, String category) {
        this.date = date;
        this.value = value;
        this.description = description;
        this.finalBalance = finalBalance;
        this.category = category;
    }

    public FinancialRecord(OffsetDateTime date, double value, String description, double finalBalance) {
        this(date, value, description, finalBalance, null);
    }

    public OffsetDateTime date() {
        return date;
    }

    public double value() {
        return value;
    }

    public String description() {
        return description;
    }

    public double finalBalance() {
        return finalBalance;
    }

    public String category() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (FinancialRecord) obj;
        return Objects.equals(this.date, that.date) &&
                Double.doubleToLongBits(this.value) == Double.doubleToLongBits(that.value) &&
                Objects.equals(this.description, that.description) &&
                Double.doubleToLongBits(this.finalBalance) == Double.doubleToLongBits(that.finalBalance) &&
                Objects.equals(this.category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, value, description, finalBalance, category);
    }

    @Override
    public String toString() {
        return "FinancialRecord[" +
                "date=" + date + ", " +
                "value=" + value + ", " +
                "description=" + description + ", " +
                "finalBalance=" + finalBalance + ", " +
                "category=" + category + ']';
    }

}
