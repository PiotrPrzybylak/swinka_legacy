package it.morfoza;

import java.math.BigDecimal;

public class Money {

    private BigDecimal value;

    public Money(double value) {
        this.value = new BigDecimal(value);
    }

    public Money(BigDecimal value) {
        this.value = value;
    }

    public double getDoubleValue() {
        return value.doubleValue();
    }

    public BigDecimal getBigDecimalValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + " PLN";
    }

    public Money add(Money money) {
        return new Money(value.add(money.value));
    }
}
