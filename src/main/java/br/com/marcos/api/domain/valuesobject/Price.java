package br.com.marcos.api.domain.valuesobject;

import java.math.BigDecimal;

public class Price {
    private BigDecimal value;

    public Price(BigDecimal value) {
        if (value == null) {
            throw new IllegalArgumentException("O preço do produto é obrigatório.");
        }

        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero.");
        }
        this.value = value;
    }

    public BigDecimal getValue() {
        return this.value;
    }
}
