package br.com.marcos.api.domain.gateway;

import br.com.marcos.api.domain.valuesobject.Password;

public interface PasswordHasher {
    Password hash(Password password);

    boolean matches(String password, String hash);
}
