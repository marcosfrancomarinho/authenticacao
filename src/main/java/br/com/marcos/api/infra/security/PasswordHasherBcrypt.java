package br.com.marcos.api.infra.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.marcos.api.domain.gateway.PasswordHasher;

public class PasswordHasherBcrypt implements PasswordHasher {
    private BCryptPasswordEncoder encoder;

    public PasswordHasherBcrypt() {
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public String hash(String password) {
        String hashString = this.encoder.encode(password);
        return hashString;
    }

    @Override
    public boolean matches(String password, String hash) {
        return this.encoder.matches(password, hash);
    }

}
