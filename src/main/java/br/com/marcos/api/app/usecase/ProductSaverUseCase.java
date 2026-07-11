package br.com.marcos.api.app.usecase;

import java.util.Optional;

import br.com.marcos.api.app.dtos.ProductSaverInput;
import br.com.marcos.api.app.dtos.ProductSaverOutput;
import br.com.marcos.api.domain.entities.Product;
import br.com.marcos.api.domain.exceptions.ProductAlreadyExistsException;
import br.com.marcos.api.domain.repository.ProductFinder;
import br.com.marcos.api.domain.repository.ProductSaver;

public class ProductSaverUseCase {
    private ProductSaver productSaver;
    private ProductFinder productFinder;

    public ProductSaverUseCase(ProductSaver productSaver, ProductFinder productFinder) {
        this.productSaver = productSaver;
        this.productFinder = productFinder;
    }

    public ProductSaverOutput save(ProductSaverInput payload) {
        Product product = new Product(payload.name(), payload.price(), payload.description());
        Optional<Product> productFound = this.productFinder.byName(product.getName());
        if (!productFound.isEmpty()) {
            throw new ProductAlreadyExistsException();
        }
        Product productRegistered = this.productSaver.save(product);
        return new ProductSaverOutput(productRegistered.getId());

    }
}
