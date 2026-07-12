package br.com.marcos.api.domain.valuesobject;

public class Description {
    private String value;

    public Description(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Descrição do produto é obrigatória.");
        }
        if (value.length() > 255) {
            throw new IllegalArgumentException("A descrição do produto deve ter no máximo 255 caracteres.");
        }
        this.value = value.trim();
    }

    public String getValue() {
        return this.value;
    }

}
