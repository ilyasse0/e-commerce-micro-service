package com.ecom.ecommerce.customer;

import com.ecom.ecommerce.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;


    public String createCustomer(@Valid CustomerRequest request) {
        return customerRepository.save(mapper.toCustomer(request)).getId();
    }

    public void updateCustomer(@Valid CustomerRequest request) {

        var customer = customerRepository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException("No Customer found with id " + request.id()));

//        customer.setFirstName(request.getFirstName());
//        customer.setLastName(request.getLastName());
//        customer.setEmail(request.getEmail());
//        customerRepository.save(customer);

        // here ill use methode emerge to make the update more safe

        mergerCustomer(customer, request);
        customerRepository.save(customer);


    }

    // this methode is the safe way to merge the customer before the update
    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.getFirstName()))
            customer.setFirstName(request.getFirstName());

        if (StringUtils.isNotBlank(request.getLastName()))
            customer.setLastName(request.getLastName());

        if (StringUtils.isNotBlank(request.getEmail()))
            customer.setEmail(request.getEmail());

        if (request.address() != null)
            customer.setAddress(request.address());

    }

    public List<CustomerResponse> findAllCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existById(String customerId) {
        return customerRepository.findById(customerId)
                .isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException("No Customer found with id " + customerId));

    }

    public void deleteCustomer(String customerId) {
        customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("No Customer found with id " + customerId));

        customerRepository.deleteById(customerId);
    }
}
