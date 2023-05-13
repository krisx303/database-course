package com.group.travels.domain.log;

import com.group.travels.domain.booking.BookingRepository;
import com.group.travels.domain.booking.BookingStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogModule {
    @Bean
    LogStorage logStorage(LogRepository logRepository) {
        return new LogStorage(logRepository);
    }

}