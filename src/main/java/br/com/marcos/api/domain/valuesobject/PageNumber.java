package br.com.marcos.api.domain.valuesobject;

public class PageNumber {
    private int value;

    public PageNumber(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("O número da pagina deve ser maior ou igual a zero.");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
