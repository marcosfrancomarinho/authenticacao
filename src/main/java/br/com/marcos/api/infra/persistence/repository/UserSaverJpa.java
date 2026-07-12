package br.com.marcos.api.infra.persistence.repository;

import br.com.marcos.api.domain.entities.User;
import br.com.marcos.api.domain.repository.UserSaver;
import br.com.marcos.api.domain.valuesobject.Email;
import br.com.marcos.api.domain.valuesobject.Id;
import br.com.marcos.api.domain.valuesobject.Name;
import br.com.marcos.api.domain.valuesobject.Password;
import br.com.marcos.api.infra.persistence.entities.UserEntity;

public class UserSaverJpa implements UserSaver {
    private UserRepository userRepository;

    public UserSaverJpa(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = new UserEntity(
                user.getName(),
                user.getEmail(),
                user.getPassword());

        UserEntity userRegistered = this.userRepository.save(userEntity);

        return new User(
                new Id(userRegistered.getId()),
                new Name(userRegistered.getName()),
                new Email(userRegistered.getEmail()),
                Password.fromHash(userRegistered.getPassword()));
    }

}
