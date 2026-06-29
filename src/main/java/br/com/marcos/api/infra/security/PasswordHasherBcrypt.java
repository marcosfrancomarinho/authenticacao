package br.com.marcos.api.infra.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.marcos.api.domain.gateway.PasswordHasher;
import br.com.marcos.api.domain.valuesobject.Password;

public class PasswordHasherBcrypt implements PasswordHasher {
    private BCryptPasswordEncoder encoder;

    public PasswordHasherBcrypt() {
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public Password hash(Password password) {
        String hashString = this.encoder.encode(password.getValue());
        return Password.fromHash(hashString);
    }

    @Override
    public boolean matches(String password, String hash) {
        return this.encoder.matches(password, hash);
    }

}
