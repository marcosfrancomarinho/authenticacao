package br.com.marcos.api.infra.persistence.repository;

import br.com.marcos.api.domain.entities.Product;
import br.com.marcos.api.domain.repository.ProductSaver;
import br.com.marcos.api.domain.valuesobject.Description;
import br.com.marcos.api.domain.valuesobject.Id;
import br.com.marcos.api.domain.valuesobject.NameProduct;
import br.com.marcos.api.domain.valuesobject.Price;
import br.com.marcos.api.infra.persistence.entities.ProductEntity;

public class ProductSaverJpa implements ProductSaver {
    private ProductRepository productRepository;

    public ProductSaverJpa(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {

        ProductEntity productEntity = new ProductEntity(
                product.getName(),
                product.getPrice(),
                product.getDescription());

        ProductEntity productRegistered = this.productRepository.save(productEntity);

        return new Product(
                new Id(productRegistered.getId()),
                new NameProduct(productRegistered.getName()),
                new Price(productRegistered.getPrice()),
                new Description(productRegistered.getDescription()));
    }

}
