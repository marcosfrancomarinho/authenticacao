package br.com.marcos.api.domain.entities;

import br.com.marcos.api.domain.valuesobject.Email;
import br.com.marcos.api.domain.valuesobject.Id;
import br.com.marcos.api.domain.valuesobject.Name;
import br.com.marcos.api.domain.valuesobject.Password;

public class User {

    private Id id;
    private Name name;
    private Email email;
    private Password password;

    public User(Name name, Email email, Password password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(Id id, Name name, Email email, Password password) {
        this(name, email, password);
        this.id = id;
    }

    public Long getId() {
        if (id == null) {
            throw new IllegalStateException("Usuário ainda não possui id.");
        }
        return id.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    public String getEmail() {
        return email.getValue();
    }

    public String getPassword() {
        return password.getValue();
    }

    public void changePasswordHash(String passwordHash) {
        if (passwordHash == null || passwordHash.isBlank()) {
            throw new IllegalArgumentException("Hash da senha é obrigatório.");
        }
        this.password = Password.fromHash(passwordHash);
    }
}