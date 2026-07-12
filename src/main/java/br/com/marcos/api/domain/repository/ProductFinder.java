package br.com.marcos.api.domain.repository;

import java.util.Optional;

import br.com.marcos.api.domain.entities.Product;
import br.com.marcos.api.domain.valuesobject.Id;
import br.com.marcos.api.domain.valuesobject.NameProduct;

public interface ProductFinder {
    Optional<Product> byName(NameProduct name);

    Optional<Product> byId(Id productId);
}
