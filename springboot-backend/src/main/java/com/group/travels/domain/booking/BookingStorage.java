package com.group.travels.domain.booking;

import com.group.travels.domain.customer.Customer;
import com.group.travels.domain.travel.Travel;

import java.time.LocalDateTime;
import java.util.List;

public class BookingStorage {

    private final BookingRepository bookingRepository;

    public BookingStorage(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> findAll(){
        return bookingRepository.findAll();
    }

    public Booking findByID(Long id){
        return bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));
    }

    public void delete(Long id) {
        Booking booking = findByID(id);
        bookingRepository.delete(booking);
    }

    public Booking create(Customer customer, Travel travel) {
        Booking booking = Booking.builder()
                .bookingDate(LocalDateTime.now())
                .bookingState(BookingState.NEW)
                .customer(customer)
                .travel(travel).build();
        return bookingRepository.save(booking);
    }

    public Booking changeBookingState(Booking booking, BookingState state) {
        booking.setBookingState(state);
        return bookingRepository.save(booking);
    }
}