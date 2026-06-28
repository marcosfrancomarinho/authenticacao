package br.com.marcos.api.infra.http.controllers;

import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthenticate {

    @GetMapping("/user")
    public Map<String, String> authUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = String.valueOf(auth.getPrincipal());
        return Map.of("userId", userId);
    }
}
