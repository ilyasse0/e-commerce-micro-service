package com.ecommerce.order;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductMapper {


    public Order toOrder(OrderRequest request) {
        return Order.builder()
                .id(request.id())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethode(request.paymentMethode())
                .customerId(request.customerId())
                //.orderLines()
                .createdDate(LocalDateTime.now())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethode(),
                order.getCustomerId()
        );
    }
}
