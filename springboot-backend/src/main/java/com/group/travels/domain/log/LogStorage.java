package com.group.travels.domain.log;

import com.group.travels.domain.travel.Travel;
import com.group.travels.domain.travel.TravelNotFoundException;

public class LogStorage {

    private final LogRepository logRepository;

    public LogStorage(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public BookingLog findByID(Long id){
        return logRepository.findById(id)
                .orElseThrow(() -> new TravelNotFoundException(id));
    }
}