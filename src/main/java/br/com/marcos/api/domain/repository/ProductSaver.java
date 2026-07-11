package br.com.marcos.api.domain.repository;

import br.com.marcos.api.domain.entities.Product;

public interface ProductSaver {
    Product save(Product product);
}
