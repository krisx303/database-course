package com.group.travels.domain.booking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingModule {
    @Bean
    BookingStorage bookingService(BookingRepository bookingRepository) {
        return new BookingStorage(bookingRepository);
    }

}
