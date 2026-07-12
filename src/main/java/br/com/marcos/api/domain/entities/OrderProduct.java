package br.com.marcos.api.domain.entities;

import java.math.BigDecimal;

import br.com.marcos.api.domain.valuesobject.Id;
import br.com.marcos.api.domain.valuesobject.Quantity;
import br.com.marcos.api.domain.valuesobject.UnitPrice;

public class OrderProduct {
    private Id id;
    private Product product;
    private Quantity quantity;
    private UnitPrice unitPrice;

    public OrderProduct(Product product, Quantity quantity, UnitPrice unitPrice) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public OrderProduct(Id id, Product product, Quantity quantity, UnitPrice unitPrice) {
        this(product, quantity, unitPrice);
        this.id = id;
    }

    public Long getId() {
        if (id == null) {
            throw new IllegalStateException("Pedido do produto ainda não possui id.");
        }
        return id.getValue();
    }

    public Long getProductId() {
        return product.getId();
    }

    public String getProductName() {
        return product.getName();
    }

    public String getProductDescription() {
        return product.getDescription();
    }

    public BigDecimal getProductPrice() {
        return product.getPrice();
    }

    public int getQuantity() {
        return quantity.getValue();
    }

    public BigDecimal getUnitPrice() {
        return unitPrice.getValue();
    }

    public BigDecimal getTotalPrice() {
        BigDecimal quantityBigDecimal = BigDecimal.valueOf(this.quantity.getValue());
        BigDecimal price = this.unitPrice.getValue();
        BigDecimal total = price.multiply(quantityBigDecimal);
        return total;
    }
}
