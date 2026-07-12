package br.com.marcos.api.infra.persistence.repository;

import br.com.marcos.api.domain.entities.Order;
import br.com.marcos.api.domain.entities.OrderProduct;
import br.com.marcos.api.domain.repository.OrderSaver;
import br.com.marcos.api.domain.valuesobject.Id;
import br.com.marcos.api.infra.persistence.entities.OrderEntity;
import br.com.marcos.api.infra.persistence.entities.OrderProductEntity;
import br.com.marcos.api.infra.persistence.entities.ProductEntity;
import jakarta.transaction.Transactional;

public class OrderSaverJpa implements OrderSaver {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public OrderSaverJpa(
            OrderRepository orderRepository,
            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Id save(Order order) {

        OrderEntity orderEntity = new OrderEntity(order.calculateTotalPrice());
        for (OrderProduct orderProduct : order.getOrderProducts()) {

            ProductEntity productEntity = this.productRepository.findById(orderProduct.getProductId()).orElseThrow();

            OrderProductEntity orderProductEntity = new OrderProductEntity(
                    orderEntity,
                    productEntity,
                    orderProduct.getQuantity(),
                    orderProduct.getUnitPrice());

            orderEntity.addItem(orderProductEntity);
        }

        OrderEntity orderSaved = this.orderRepository.save(orderEntity);
        return new Id(orderSaved.getId());
    }

}
