package com.group.travels.domain.log;

import com.group.travels.domain.booking.Booking;
import com.group.travels.domain.travel.TravelNotFoundException;

import java.time.LocalDateTime;

public class LogStorage {

    private final LogRepository logRepository;

    public LogStorage(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public BookingLog findByID(Long id){
        return logRepository.findById(id)
                .orElseThrow(() -> new TravelNotFoundException(id));
    }

    public void logCreate(Booking saved) {
        BookingLog log = new BookingLog();
        log.setBooking(saved);
        log.setBookingDate(LocalDateTime.now());
        log.setBookingState(saved.getBookingState());
        logRepository.save(log);
    }

    public void logChange(Booking changed){
        BookingLog log = new BookingLog();
        log.setBooking(changed);
        log.setBookingDate(LocalDateTime.now());
        log.setBookingState(changed.getBookingState());
        logRepository.save(log);
    }
}