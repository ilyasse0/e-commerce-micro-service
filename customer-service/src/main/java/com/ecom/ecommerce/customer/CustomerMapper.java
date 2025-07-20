package com.ecom.ecommerce.customer;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {


    public Customer toCustomer(@Valid CustomerRequest request) {
        if(request == null) return null;
        return Customer
                .builder()
                .id(request.id())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .address(request.getAddress())
                .build();
    }


    public CustomerResponse fromCustomer(Customer customer) {
        return CustomerResponse
                .builder()
                .id(customer.getId())
                .address(customer.getAddress())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .build();
    }
}