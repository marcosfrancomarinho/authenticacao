package br.com.marcos.api.domain.valuesobject;

public class Name {
    private String value;

    public Name(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        this.value = value.trim();
    }

    public String getValue() {
        return this.value;
    }

}
