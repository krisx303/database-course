package com.group.travels.domain.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerModule {
    @Bean
    CustomerStorage customerService(CustomerRepository customerRepository) {
        return new CustomerStorage(customerRepository);
    }

}
