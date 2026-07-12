package br.com.marcos.api.domain.valuesobject;

public class NameProduct {
    private String value;

    public NameProduct(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório.");
        }
        if (value.length() > 100) {
            throw new IllegalArgumentException("O nome do produto deve ter no máximo 100 caracteres.");
        }
        this.value = value.trim();
    }

    public String getValue() {
        return this.value;
    }

}
