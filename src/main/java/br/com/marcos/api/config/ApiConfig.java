package br.com.marcos.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.marcos.api.app.usecase.ProductSaverUseCase;
import br.com.marcos.api.app.usecase.UserLoginUseCase;
import br.com.marcos.api.app.usecase.UserSaverUseCase;
import br.com.marcos.api.domain.gateway.AuthenticationGateway;
import br.com.marcos.api.domain.gateway.PasswordHasher;
import br.com.marcos.api.domain.repository.ProductFinder;
import br.com.marcos.api.domain.repository.ProductSaver;
import br.com.marcos.api.domain.repository.UserFinder;
import br.com.marcos.api.domain.repository.UserSaver;
import br.com.marcos.api.infra.auth.AuthenticationJwt;
import br.com.marcos.api.infra.http.middleware.JwtAuthenticationFilter;
import br.com.marcos.api.infra.persistence.repository.ProductFinderJpa;
import br.com.marcos.api.infra.persistence.repository.ProductRepository;
import br.com.marcos.api.infra.persistence.repository.ProductSaverJpa;
import br.com.marcos.api.infra.persistence.repository.UserFinderJpa;
import br.com.marcos.api.infra.persistence.repository.UserRepository;
import br.com.marcos.api.infra.persistence.repository.UserSaverJpa;
import br.com.marcos.api.infra.security.PasswordHasherBcrypt;

@Configuration
public class ApiConfig {

    @Bean
    public PasswordHasher passwordHasher() {
        return new PasswordHasherBcrypt();
    }

    @Bean
    public UserSaver userSaver(UserRepository userRepository) {
        return new UserSaverJpa(userRepository);
    }

    @Bean
    public UserFinder userFinder(UserRepository userRepository) {
        return new UserFinderJpa(userRepository);
    }

    @Bean
    public AuthenticationGateway authenticationGateway(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiration) {
        return new AuthenticationJwt(secret, expiration);
    }

    @Bean
    public UserLoginUseCase userLoginUseCase(UserFinder userFinder, PasswordHasher passwordHasher,
            AuthenticationGateway authenticationGateway) {
        return new UserLoginUseCase(userFinder, passwordHasher, authenticationGateway);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationGateway authenticationGateway) {
        return new JwtAuthenticationFilter(authenticationGateway);
    }

    @Bean
    public UserSaverUseCase userSaverUseCase(UserSaver userSaver, PasswordHasher passwordHasher,
            UserFinder userFinder) {
        return new UserSaverUseCase(userSaver, passwordHasher, userFinder);
    }

    @Bean
    public ProductSaver productSaver(ProductRepository productRepository) {
        return new ProductSaverJpa(productRepository);
    }

    @Bean
    ProductFinder productFinder(ProductRepository productRepository) {
        return new ProductFinderJpa(productRepository);
    }

    @Bean
    ProductSaverUseCase productSaverUseCase(ProductSaver productSaver, ProductFinder productFinder) {
        return new ProductSaverUseCase(productSaver, productFinder);
    }

}
