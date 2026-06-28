package br.com.marcos.api.app.usecase;

import java.util.Optional;

import br.com.marcos.api.app.dtos.UserSaverInput;
import br.com.marcos.api.app.dtos.UserSaverOutput;
import br.com.marcos.api.domain.entities.User;
import br.com.marcos.api.domain.exceptions.UserAlreadyExistsException;
import br.com.marcos.api.domain.gateway.PasswordHasher;
import br.com.marcos.api.domain.repository.UserFinder;
import br.com.marcos.api.domain.repository.UserSaver;
import br.com.marcos.api.domain.valuesobject.Email;
import br.com.marcos.api.domain.valuesobject.Name;
import br.com.marcos.api.domain.valuesobject.Password;

public class UserSaverUseCase {
    private UserSaver userSaver;
    private PasswordHasher passwordHasher;
    private UserFinder userFinder;

    public UserSaverUseCase(UserSaver userSaver, PasswordHasher passwordHasher, UserFinder userFinder) {
        this.userSaver = userSaver;
        this.passwordHasher = passwordHasher;
        this.userFinder = userFinder;
    }

    public UserSaverOutput save(UserSaverInput payload) {
        Email email = new Email(payload.email());
        Password password = new Password(payload.password());
        Name name = new Name(payload.name());

        Optional<User> userFound = this.userFinder.byEmail(email);
        if (!userFound.isEmpty()) {
            throw new UserAlreadyExistsException();
        }

        User user = new User(name, email, password);

        String encryptedPassword = this.passwordHasher.hash(payload.password());
        user.changePasswordHash(encryptedPassword);

        User userRegistered = this.userSaver.save(user);

        return new UserSaverOutput(userRegistered.getId());
    }
}
