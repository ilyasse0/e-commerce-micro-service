package com.ecommerce.payment;
import org.springframework.stereotype.Service;
@Service
public class PaymentMapper {
    public Payment toPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .id(paymentRequest.id())
                .amount(paymentRequest.amount())
                .orderId(paymentRequest.orderId())
                .paymentMethode(paymentRequest.paymentMethode())
                .build();

    }
}
