package com.ecommerce.notification;

import com.ecommerce.payment.PaymentMethode;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethode paymentMethode,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
