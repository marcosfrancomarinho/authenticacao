package br.com.marcos.api.infra.http.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcos.api.app.dtos.OrderFinderInput;
import br.com.marcos.api.app.dtos.OrderFinderOutput;
import br.com.marcos.api.app.usecase.OrderFinderUseCase;

@RestController
public class OrderFinderController {
    private OrderFinderUseCase orderFinderUseCase;

    public OrderFinderController(OrderFinderUseCase orderFinderUseCase) {
        this.orderFinderUseCase = orderFinderUseCase;
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?> execute(@PathVariable Long id) {
        try {
            OrderFinderInput payload = new OrderFinderInput(id);
            OrderFinderOutput output = this.orderFinderUseCase.find(payload);
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}
