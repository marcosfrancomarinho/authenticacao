package br.com.marcos.api.infra.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.marcos.api.domain.entities.Page;
import br.com.marcos.api.domain.entities.Product;
import br.com.marcos.api.domain.repository.ProductFinder;
import br.com.marcos.api.domain.valuesobject.Description;
import br.com.marcos.api.domain.valuesobject.Id;
import br.com.marcos.api.domain.valuesobject.NameProduct;
import br.com.marcos.api.domain.valuesobject.Price;
import br.com.marcos.api.infra.persistence.entities.ProductEntity;

public class ProductFinderJpa implements ProductFinder {
    private ProductRepository productRepository;

    public ProductFinderJpa(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> byName(NameProduct name) {
        String nameProducString = name.getValue();
        Optional<ProductEntity> productEntity = this.productRepository.findByName(nameProducString);

        return productEntity.map(
                entity -> new Product(
                        new Id(entity.getId()),
                        new NameProduct(entity.getName()),
                        new Price(entity.getPrice()),
                        new Description(entity.getDescription())));
    }

    @Override
    public Optional<Product> byId(Id productId) {
        Optional<ProductEntity> productEntity = this.productRepository.findById(productId.getValue());

        return productEntity.map(
                entity -> new Product(
                        new Id(entity.getId()),
                        new NameProduct(entity.getName()),
                        new Price(entity.getPrice()),
                        new Description(entity.getDescription())));
    }

    @Override
    public List<Product> findAll(Page page) {
        List<Product> products = new ArrayList<Product>();
        Pageable pageable = PageRequest.of(page.getNumber(), page.getSize());
        var productEntity = this.productRepository.findAll(pageable);
        productEntity.stream().forEach(entity -> {
            Id id = new Id(entity.getId());
            NameProduct name = new NameProduct(entity.getName());
            Description description = new Description(entity.getDescription());
            Price price = new Price(entity.getPrice());
            Product product = new Product(id, name, price, description);
            products.add(product);
        });
        return products;
    }

}
