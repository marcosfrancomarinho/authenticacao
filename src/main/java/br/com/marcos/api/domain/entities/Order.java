package br.com.marcos.api.domain.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.marcos.api.domain.valuesobject.Id;

public class Order {
    private Id id;
    private List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();

    public Order(Id id) {
        this.id = id;
    }

    public Order() {

    }

    public void addOrderProduct(OrderProduct orderProduct) {
        if (orderProduct == null) {
            throw new IllegalArgumentException("O item do pedido não pode ser nulo.");
        }
        this.orderProducts.add(orderProduct);
    }

    public Long getId() {
        if (id == null) {
            throw new IllegalStateException("Pedido ainda não possui id.");
        }

        return id.getValue();
    }

    public List<OrderProduct> getOrderProducts() {
        return new ArrayList<OrderProduct>(this.orderProducts);
    }

    public BigDecimal getTotalPrice() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderProduct orderProduct : orderProducts) {
            total = total.add(orderProduct.getTotalPrice());
        }
        return total;
    }

    public int getTotalQuantityProducts() {
        return orderProducts.stream().mapToInt(OrderProduct::getQuantity).sum();
    }
}
