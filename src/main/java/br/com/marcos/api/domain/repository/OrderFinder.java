package br.com.marcos.api.domain.repository;

import java.util.Optional;

import br.com.marcos.api.domain.entities.Order;
import br.com.marcos.api.domain.valuesobject.Id;

public interface OrderFinder {
    Optional<Order> byId(Id orderId);
}
