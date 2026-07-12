package br.com.marcos.api.app.dtos;

import java.math.BigDecimal;

public record OrderSaverInput(Long productId, int quantity, BigDecimal unitPrice) {
}
