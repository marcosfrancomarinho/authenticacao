package br.com.marcos.api.domain.entities;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;

    public Product(String name, BigDecimal price, String description) {
        validate(name, price, description);
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public Product(Long id, String name, BigDecimal price, String description) {
        this(name, price, description);
        this.id = id;
    }

    private void validate(String name, BigDecimal price, String description) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("O nome deve ter no máximo 100 caracteres.");
        }
        if (price == null) {
            throw new IllegalArgumentException("O preço do produto é obrigatório.");
        }
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O preço deve ser igual ou maior que zero.");
        }

        if (description != null && description.trim().length() > 255) {
            throw new IllegalArgumentException("A descrição deve ter no máximo 255 caracteres.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
