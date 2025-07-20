package com.ecom.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;



public record CustomerRequest (

     String id,
     @Getter
     @NotNull(message = "Customer first name is required !")
     String firstName,

     @Getter
     @NotNull(message = "Customer last name is required !")
     String lastName,

     @Getter
     @Email(message = "Email must be valid !")
     @NotNull(message = "Customer email is required !")
     String email,

     @Getter
     Address address
     ){

}
