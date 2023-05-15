package com.group.travels.api.customer;

import com.group.travels.domain.customer.Customer;

public record CustomerResponse(Long id,
                               String customerName,
                               String customerEmail,
                               String customerPhone) {
    public CustomerResponse(Customer customer){
        this(customer.getCustomer_id(),
                customer.getCustomerName(),
                customer.getCustomerEmail(),
                customer.getCustomerPhone());
    }
}
