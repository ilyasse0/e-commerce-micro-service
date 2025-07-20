package com.ecommerce.payment;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(

        String id,
        @NotNull(message = "first name is required")
        String firstName,
        @NotNull(message = "last name is required")

        String lastName,
        @NotNull(message = "email is required")
        @Email(message = "Email is not valid")
        String email

) {
}
