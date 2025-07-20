package com.ecommerce.payment;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethode paymentMethode,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}
