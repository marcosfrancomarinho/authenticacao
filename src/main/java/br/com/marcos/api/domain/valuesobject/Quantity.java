package br.com.marcos.api.domain.valuesobject;

public final class Quantity {
    private final int value;

    public Quantity(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
