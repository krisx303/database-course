package com.group.travels.domain.customer;

import com.group.travels.api.customer.CustomerRequest;

import java.util.List;

public class CustomerStorage {

    private final CustomerRepository customerRepository;

    public CustomerStorage(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer findByID(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    public Customer create(CustomerRequest details) {
        Customer toSave = Customer.builder()
                .customerName(details.customerName())
                .customerEmail(details.customerEmail())
                .customerPhone(details.customerPhone()).build();
        return customerRepository.save(toSave);
    }

    public Customer update(Long id, CustomerRequest details) {
        Customer toUpdate = findByID(id);
        toUpdate.setCustomerName(details.customerName());
        toUpdate.setCustomerEmail(details.customerEmail());
        toUpdate.setCustomerPhone(details.customerPhone());
        return customerRepository.save(toUpdate);
    }

    public void delete(Long id) {
        Customer customer = findByID(id);
        customerRepository.delete(customer);
    }
}