package com.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "Product is require")
        Integer productId,
        @NotNull(message = "the quantity is required")
        @Positive(message = "the quantity must be positive")
        double quantity
) {
}
