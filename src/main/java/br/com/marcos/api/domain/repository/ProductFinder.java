package br.com.marcos.api.domain.repository;

import java.util.Optional;

import br.com.marcos.api.domain.entities.Product;

public interface ProductFinder {
    Optional<Product> byName(String name);
}
