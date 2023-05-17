package com.group.travels.domain.discount;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscountModule {

    @Bean
    DiscountStorage discountStorage(DiscountRepository discountRepository) {
        return new DiscountStorage(discountRepository);
    }
}
