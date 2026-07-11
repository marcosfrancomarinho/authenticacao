package br.com.marcos.api.infra.http.controllers;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcos.api.app.dtos.ProductSaverInput;
import br.com.marcos.api.app.dtos.ProductSaverOutput;
import br.com.marcos.api.app.usecase.ProductSaverUseCase;

record InnerProductSaverController(String name, String price, String description) {

}

@RestController
public class ProductSaverController {
    private ProductSaverUseCase productSaverUseCase;

    public ProductSaverController(ProductSaverUseCase productSaverUseCase) {
        this.productSaverUseCase = productSaverUseCase;
    }

    @PostMapping("/product")
    public ResponseEntity<?> execute(@RequestBody InnerProductSaverController body) {
        try {
            BigDecimal pricDecimal = new BigDecimal(body.price());
            ProductSaverInput input = new ProductSaverInput(body.name(), pricDecimal, body.description());
            ProductSaverOutput output = this.productSaverUseCase.save(input);
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}
