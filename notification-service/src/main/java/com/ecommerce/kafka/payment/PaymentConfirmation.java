package com.ecommerce.kafka.payment;

import java.math.BigDecimal;

public record PaymentConfirmation(

        String orderReference,
        BigDecimal amount,
        PaymentMethode methode,
        String customerFirstName,

        String customerLastName,
        String customerEmail

) {
}
