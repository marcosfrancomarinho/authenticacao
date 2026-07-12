package br.com.marcos.api.app.dtos;

import java.math.BigDecimal;
import java.util.List;

public record OrderFinderOutput(
                Long orderId,
                List<OrderItemOutput> products,
                BigDecimal totalPrice,
                int totalQuantity) {

}
