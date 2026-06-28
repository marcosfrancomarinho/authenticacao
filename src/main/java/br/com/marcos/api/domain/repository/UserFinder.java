package br.com.marcos.api.domain.repository;

import java.util.Optional;

import br.com.marcos.api.domain.entities.User;
import br.com.marcos.api.domain.valuesobject.Email;

public interface UserFinder {
    Optional<User> byEmail(Email email);
}
