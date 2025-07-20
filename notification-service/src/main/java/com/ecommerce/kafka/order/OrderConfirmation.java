package com.ecommerce.kafka.order;

import com.ecommerce.kafka.payment.PaymentMethode;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethode paymentMethode,
        Customer customer,
        List<Product> products

) {
}
