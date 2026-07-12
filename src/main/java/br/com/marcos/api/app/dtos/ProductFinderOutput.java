package br.com.marcos.api.app.dtos;

import java.math.BigDecimal;

public record ProductFinderOutput(Long productId, String name, BigDecimal price, String description) {

}
