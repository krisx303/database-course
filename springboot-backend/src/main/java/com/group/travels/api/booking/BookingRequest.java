package com.group.travels.api.booking;

public interface BookingRequest {
    public static record CreateBookingRequest(Long travelID, Long customerID){}
}