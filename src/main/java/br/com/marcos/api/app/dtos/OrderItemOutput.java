package br.com.marcos.api.app.dtos;

import java.math.BigDecimal;

public record OrderItemOutput(
                Long productId,
                String name,
                BigDecimal unitPrice,
                String description,
                int quantity,
                BigDecimal totalPrice) {
}