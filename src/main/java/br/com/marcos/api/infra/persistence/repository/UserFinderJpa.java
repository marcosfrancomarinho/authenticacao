package br.com.marcos.api.infra.persistence.repository;

import java.util.Optional;

import br.com.marcos.api.domain.entities.User;
import br.com.marcos.api.domain.repository.UserFinder;
import br.com.marcos.api.domain.valuesobject.Email;
import br.com.marcos.api.domain.valuesobject.Name;
import br.com.marcos.api.domain.valuesobject.Password;

public class UserFinderJpa implements UserFinder {
    private UserRepository userRepository;

    public UserFinderJpa(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> byEmail(Email email) {
        return this.userRepository.findByEmail(email.getValue())
                .map(entity -> new User(
                        entity.getId(),
                        new Name(entity.getName()),
                        new Email(entity.getEmail()),
                        Password.fromHash(entity.getPassword())));
    }

}
