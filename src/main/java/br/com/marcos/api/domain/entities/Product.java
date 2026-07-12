package br.com.marcos.api.domain.entities;

import java.math.BigDecimal;

import br.com.marcos.api.domain.valuesobject.Description;
import br.com.marcos.api.domain.valuesobject.Id;
import br.com.marcos.api.domain.valuesobject.NameProduct;
import br.com.marcos.api.domain.valuesobject.Price;

public class Product {
    private Id id;
    private NameProduct name;
    private Price price;
    private Description description;

    public Product(NameProduct name, Price price, Description description) {
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public Product(Id id, NameProduct name, Price price, Description description) {
        this(name, price, description);
        this.id = id;
    }

    public Long getId() {
        if (id == null) {
            throw new IllegalStateException("Produto ainda não possui id.");
        }
        return id.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    public BigDecimal getPrice() {
        return price.getValue();
    }

    public String getDescription() {
        return description.getValue();
    }
}
