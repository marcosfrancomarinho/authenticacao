package br.com.marcos.api.domain.valuesobject;

public final class Id {
    private final Long value;

    public Id(Long value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("O ID deve ser um número maior que zero.");
        }
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

}
