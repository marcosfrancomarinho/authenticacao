package br.com.marcos.api.domain.valuesobject;

public class Password {
    private String value;

    public Password(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Senha é obrigatória.");
        }
        if (value.trim().length() < 8) {
            throw new IllegalArgumentException("Senha tem que conter minimo 8 caracteres.");
        }
        this.value = value.trim();
    }

    public static Password fromHash(String hash) {
        return new Password(hash);
    }

    public String getValue() {
        return this.value;
    }
}
