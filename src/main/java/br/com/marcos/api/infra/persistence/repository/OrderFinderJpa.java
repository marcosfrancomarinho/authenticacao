package br.com.marcos.api.infra.persistence.repository;

import java.util.Optional;

import br.com.marcos.api.domain.entities.Order;
import br.com.marcos.api.domain.entities.OrderProduct;
import br.com.marcos.api.domain.entities.Product;
import br.com.marcos.api.domain.repository.OrderFinder;
import br.com.marcos.api.domain.valuesobject.Description;
import br.com.marcos.api.domain.valuesobject.Id;
import br.com.marcos.api.domain.valuesobject.NameProduct;
import br.com.marcos.api.domain.valuesobject.Price;
import br.com.marcos.api.domain.valuesobject.Quantity;
import br.com.marcos.api.domain.valuesobject.UnitPrice;
import br.com.marcos.api.infra.persistence.entities.OrderEntity;

public class OrderFinderJpa implements OrderFinder {

    private OrderRepository orderRepository;

    public OrderFinderJpa(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> byId(Id orderId) {

        return orderRepository.findById(orderId.getValue())
                .map(this::toDomain);
    }

    private Order toDomain(OrderEntity entity) {

        Order order = new Order(new Id(entity.getId()));

        entity.getItems().forEach(item -> {

            Product product = new Product(
                    new Id(item.getProduct().getId()),
                    new NameProduct(item.getProduct().getName()),
                    new Price(item.getUnitPrice()),
                    new Description(item.getProduct().getDescription()));

            OrderProduct orderProduct = new OrderProduct(
                    new Id(item.getId()),
                    product,
                    new Quantity(item.getQuantity()),
                    new UnitPrice(item.getUnitPrice()));

            order.addOrderProduct(orderProduct);
        });

        return order;
    }
}