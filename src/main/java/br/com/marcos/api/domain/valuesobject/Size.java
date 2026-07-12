package br.com.marcos.api.domain.valuesobject;

public class Size {
    private int value;

    public Size(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("A quantidade de pagina deve ser maior que zero.");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
