package br.com.marcos.api.infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.marcos.api.infra.persistence.entities.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    
}
