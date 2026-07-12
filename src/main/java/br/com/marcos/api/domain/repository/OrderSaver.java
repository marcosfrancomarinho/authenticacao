package br.com.marcos.api.domain.repository;

import br.com.marcos.api.domain.entities.Order;
import br.com.marcos.api.domain.valuesobject.Id;

public interface OrderSaver {
    Id save(Order order);
}
