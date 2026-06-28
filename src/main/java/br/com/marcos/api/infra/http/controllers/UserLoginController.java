package br.com.marcos.api.infra.http.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcos.api.app.dtos.UserLoginInput;
import br.com.marcos.api.app.dtos.UserLoginOutput;
import br.com.marcos.api.app.usecase.UserLoginUseCase;

@RestController
public class UserLoginController {
    private UserLoginUseCase userLoginUseCase;

    public UserLoginController(UserLoginUseCase userLoginUseCase) {
        this.userLoginUseCase = userLoginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<?> execute(@RequestBody UserLoginInput payload) {
        try {
            UserLoginOutput response = this.userLoginUseCase.login(payload);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}
