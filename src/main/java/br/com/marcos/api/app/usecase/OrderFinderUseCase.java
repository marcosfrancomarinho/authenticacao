package br.com.marcos.api.app.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.marcos.api.app.dtos.OrderFinderInput;
import br.com.marcos.api.app.dtos.OrderFinderOutput;
import br.com.marcos.api.app.dtos.OrderItemOutput;
import br.com.marcos.api.domain.entities.Order;
import br.com.marcos.api.domain.entities.OrderProduct;
import br.com.marcos.api.domain.exceptions.OrderNotFoundException;
import br.com.marcos.api.domain.repository.OrderFinder;
import br.com.marcos.api.domain.valuesobject.Id;

public class OrderFinderUseCase {
    private OrderFinder orderFinder;

    public OrderFinderUseCase(OrderFinder orderFinder) {
        this.orderFinder = orderFinder;
    }

    public OrderFinderOutput find(OrderFinderInput payload) {
        Id orderId = new Id(payload.orderId());
        Optional<Order> orderFound = this.orderFinder.byId(orderId);

        if (orderFound.isEmpty()) {
            throw new OrderNotFoundException(orderId.getValue());
        }
        Order order = orderFound.get();

        List<OrderItemOutput> products = new ArrayList<>();

        for (OrderProduct orderProduct : order.getOrderProducts()) {
            OrderItemOutput product = new OrderItemOutput(
                    orderProduct.getProductId(),
                    orderProduct.getProductName(),
                    orderProduct.getProductPrice(),
                    orderProduct.getProductDescription(),
                    orderProduct.getQuantity(),
                    orderProduct.getTotalPrice());

            products.add(product);
        }
        return new OrderFinderOutput(order.getId(), products, order.getTotalPrice(), order.getTotalQuantityProducts());
    }

}
