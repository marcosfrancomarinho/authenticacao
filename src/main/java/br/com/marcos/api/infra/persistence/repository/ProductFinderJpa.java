package br.com.marcos.api.infra.persistence.repository;

import java.util.Optional;

import br.com.marcos.api.domain.entities.Product;
import br.com.marcos.api.domain.repository.ProductFinder;

public class ProductFinderJpa implements ProductFinder {
    private ProductRepository productRepository;

    public ProductFinderJpa(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> byName(String name) {
        return this.productRepository.findByName(name).map(
                entity -> new Product(
                        entity.getId(),
                        entity.getName(),
                        entity.getPrice(),
                        entity.getDescription()));
    }

}
