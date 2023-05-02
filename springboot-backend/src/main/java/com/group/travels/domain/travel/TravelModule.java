package com.group.travels.domain.travel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TravelModule {
    @Bean
    TravelStorage travelService(TravelRepository travelRepository) {
        return new TravelStorage(travelRepository);
    }

}
