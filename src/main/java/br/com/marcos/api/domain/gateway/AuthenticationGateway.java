package br.com.marcos.api.domain.gateway;

public interface AuthenticationGateway {
    String generateToken(Long userId);

    String validateToken(String token);

}
