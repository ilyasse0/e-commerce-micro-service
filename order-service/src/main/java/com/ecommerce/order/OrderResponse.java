package com.ecommerce.order;

import lombok.Setter;

import java.math.BigDecimal;

public record OrderResponse(
        @Setter
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethode paymentMethode,
        String customerId
) {
}
