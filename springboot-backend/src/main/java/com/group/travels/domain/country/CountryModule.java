package com.group.travels.domain.country;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountryModule {

    @Bean
    CountryStorage countryService(CountryRepository countryRepository) {
        return new CountryStorage(countryRepository);
    }

}
