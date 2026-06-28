package br.com.marcos.api.domain.repository;

import br.com.marcos.api.domain.entities.User;

public interface UserSaver {
    User save(User user);
}
