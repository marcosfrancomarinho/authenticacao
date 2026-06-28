package br.com.marcos.api.app.dtos;

public record UserLoginOutput(String token, Long userId, String email, String name) {

}
