package br.com.marcos.api.app.dtos;

import java.math.BigDecimal;

public record ProductSaverInput(String name, BigDecimal price, String description) {

}
