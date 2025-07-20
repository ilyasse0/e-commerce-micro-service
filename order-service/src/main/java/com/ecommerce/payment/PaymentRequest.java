package com.ecommerce.payment;

import com.ecommerce.customer.CustomerResponse;
import com.ecommerce.order.PaymentMethode;

import java.math.BigDecimal;

public record PaymentRequest(

        BigDecimal amount,
        PaymentMethode paymentMethode,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
