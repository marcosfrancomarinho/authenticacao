package br.com.marcos.api.infra.http.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcos.api.app.dtos.OrderSaverInput;
import br.com.marcos.api.app.dtos.OrderSaverOutput;
import br.com.marcos.api.app.usecase.OrderSaverUseCase;

@RestController
public class OrderSaverController {
    private OrderSaverUseCase orderSaverUseCase;

    public OrderSaverController(OrderSaverUseCase orderSaverUseCase) {
        this.orderSaverUseCase = orderSaverUseCase;
    }

    @PostMapping("/orders")
    public ResponseEntity<?> execute(@RequestBody List<OrderSaverInput> body) {
        try {
            OrderSaverOutput output = this.orderSaverUseCase.save(body);
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}
