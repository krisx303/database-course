package com.group.travels.api.log;

import com.group.travels.domain.booking.Booking;
import com.group.travels.domain.booking.BookingStorage;
import com.group.travels.domain.customer.Customer;
import com.group.travels.domain.customer.CustomerStorage;
import com.group.travels.domain.log.BookingLog;
import com.group.travels.domain.log.LogStorage;
import com.group.travels.domain.travel.Travel;
import com.group.travels.domain.travel.TravelStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/logs")
public class LogController {

    @Autowired
    private LogStorage logStorage;

    @Autowired
    private TravelStorage travelStorage;

    @Autowired
    private CustomerStorage customerStorage;

    @Autowired
    private BookingStorage bookingStorage;


    @GetMapping("/travel/{id}")
    ResponseEntity<List<BookingLog>> getLogsForTravel(@PathVariable Long id) {
        Travel travel = travelStorage.findByID(id);

        List<BookingLog> logs = travel.getBookings().stream()
                .map(Booking::getId)
                .map(logStorage::findByID).toList();

        return ResponseEntity.ok(logs);
    }

    @GetMapping("/customer/{id}")
    ResponseEntity<List<BookingLog>> getLogsForCustomer(@PathVariable Long id) {
        Customer customer = customerStorage.findByID(id);

        List<BookingLog> logs = customer.getBookings().stream()
                .map(Booking::getId)
                .map(logStorage::findByID).toList();

        return ResponseEntity.ok(logs);
    }

}
