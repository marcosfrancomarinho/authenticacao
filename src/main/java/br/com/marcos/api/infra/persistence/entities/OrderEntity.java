package br.com.marcos.api.infra.persistence.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProductEntity> items = new ArrayList<>();

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    public OrderEntity() {
    }

    public OrderEntity(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public List<OrderProductEntity> getItems() {
        return items;
    }

    public void setItems(List<OrderProductEntity> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addItem(OrderProductEntity item) {
        items.add(item);
        item.setOrder(this);
    }

    public void removeItem(OrderProductEntity item) {
        items.remove(item);
        item.setOrder(null);
    }
}