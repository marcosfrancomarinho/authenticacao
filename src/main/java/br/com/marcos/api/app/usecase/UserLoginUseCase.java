package br.com.marcos.api.app.usecase;

import java.util.Optional;

import br.com.marcos.api.app.dtos.UserLoginInput;
import br.com.marcos.api.app.dtos.UserLoginOutput;
import br.com.marcos.api.domain.entities.User;
import br.com.marcos.api.domain.exceptions.InvalidCredentialsException;
import br.com.marcos.api.domain.gateway.AuthenticationGateway;
import br.com.marcos.api.domain.gateway.PasswordHasher;
import br.com.marcos.api.domain.repository.UserFinder;
import br.com.marcos.api.domain.valuesobject.Email;
import br.com.marcos.api.domain.valuesobject.Password;

public class UserLoginUseCase {
    private UserFinder userFinder;
    private PasswordHasher passwordHasher;
    private AuthenticationGateway authenticationGateway;

    public UserLoginUseCase(UserFinder userFinder, PasswordHasher passwordHasher,
            AuthenticationGateway authenticationGateway) {
        this.userFinder = userFinder;
        this.passwordHasher = passwordHasher;
        this.authenticationGateway = authenticationGateway;
    }

    public UserLoginOutput login(UserLoginInput payload) {
        Email email = new Email(payload.email());
        Password password = new Password(payload.password());

        Optional<User> userFound = this.userFinder.byEmail(email);

        if (userFound.isEmpty()) {
            throw new InvalidCredentialsException();
        }

        User user = userFound.get();

        boolean isEqual = this.passwordHasher.matches(password.getValue(), user.getPassword());

        if (!isEqual) {
            throw new InvalidCredentialsException();
        }
        String token = this.authenticationGateway.generateToken(user.getId());

        return new UserLoginOutput(token, user.getId(), user.getEmail(), user.getName());

    }
}
