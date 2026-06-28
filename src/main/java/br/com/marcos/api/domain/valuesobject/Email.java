package br.com.marcos.api.domain.valuesobject;

import java.util.regex.Pattern;

public class Email {
    private String value;

    public Email(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email é obrigatório.");
        }
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!Pattern.matches(regex, value)) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.value = value.trim();
    }

    public String getValue() {
        return this.value;
    }

}
