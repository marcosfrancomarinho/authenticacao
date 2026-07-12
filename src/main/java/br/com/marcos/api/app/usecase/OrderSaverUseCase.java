package br.com.marcos.api.app.usecase;

import java.util.List;
import java.util.Optional;

import br.com.marcos.api.app.dtos.OrderSaverInput;
import br.com.marcos.api.app.dtos.OrderSaverOutput;
import br.com.marcos.api.domain.entities.Order;
import br.com.marcos.api.domain.entities.OrderProduct;
import br.com.marcos.api.domain.entities.Product;
import br.com.marcos.api.domain.exceptions.ProductNotFoundException;
import br.com.marcos.api.domain.repository.OrderSaver;
import br.com.marcos.api.domain.repository.ProductFinder;
import br.com.marcos.api.domain.valuesobject.Id;
import br.com.marcos.api.domain.valuesobject.Quantity;
import br.com.marcos.api.domain.valuesobject.UnitPrice;

public class OrderSaverUseCase {
    private ProductFinder productFinder;
    private OrderSaver orderSaver;

    public OrderSaverUseCase(ProductFinder productFinder, OrderSaver orderSaver) {
        this.productFinder = productFinder;
        this.orderSaver = orderSaver;
    }

    public OrderSaverOutput save(List<OrderSaverInput> payload) {
        Order order = new Order();

        for (OrderSaverInput orderProductSaverInput : payload) {
            Id id = new Id(orderProductSaverInput.productId());
            Optional<Product> productFound = this.productFinder.byId(id);

            if (productFound.isEmpty()) {
                throw new ProductNotFoundException();
            }
            Product product = productFound.get();
            Quantity quantity = new Quantity(orderProductSaverInput.quantity());
            UnitPrice unitPrice = new UnitPrice(orderProductSaverInput.unitPrice());
            OrderProduct orderProduct = new OrderProduct(product, quantity, unitPrice);

            order.addOrderProduct(orderProduct);
        }

        Id orderId = this.orderSaver.save(order);

        return new OrderSaverOutput(orderId.getValue());
    }
}
