package com.ecommerce.order;

import com.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest (
         Integer id,
         String reference,
         @Positive(message = "Order amount must be positive")
         BigDecimal amount,
         @NotNull(message = "Must choose a payment methode")
         PaymentMethode paymentMethode,
         @NotNull(message = "Customer id should not be null")
         @NotEmpty(message = "Customer id should not be empty")
         @NotBlank(message = "Customer id should not be blank")
         String customerId,
         @NotEmpty(message = "you should at least purchase one product ")
         List<PurchaseRequest> products

        )
{
}
