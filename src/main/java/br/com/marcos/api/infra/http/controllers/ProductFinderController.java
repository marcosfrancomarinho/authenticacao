package br.com.marcos.api.infra.http.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcos.api.app.dtos.ProductFinderInput;
import br.com.marcos.api.app.dtos.ProductFinderOutput;
import br.com.marcos.api.app.usecase.ProductFinderUseCase;

@RestController
public class ProductFinderController {
    private ProductFinderUseCase productFinderUseCase;

    public ProductFinderController(ProductFinderUseCase productFinderUseCase) {
        this.productFinderUseCase = productFinderUseCase;
    }

    @GetMapping("/product")
    public ResponseEntity<?> execute(@RequestParam int page, @RequestParam int size) {
        try {
            ProductFinderInput productFinderInput = new ProductFinderInput(page, size);
            List<ProductFinderOutput> output = this.productFinderUseCase.findAll(productFinderInput);
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}
