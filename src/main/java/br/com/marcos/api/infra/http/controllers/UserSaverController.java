package br.com.marcos.api.infra.http.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcos.api.app.dtos.UserSaverInput;
import br.com.marcos.api.app.dtos.UserSaverOutput;
import br.com.marcos.api.app.usecase.UserSaverUseCase;

@RestController
public class UserSaverController {
    private UserSaverUseCase userSaverUseCase;

    public UserSaverController(UserSaverUseCase userSaverUseCase) {
        this.userSaverUseCase = userSaverUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<?> execute(@RequestBody UserSaverInput payload) {
        try {
            UserSaverOutput response = this.userSaverUseCase.save(payload);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}
