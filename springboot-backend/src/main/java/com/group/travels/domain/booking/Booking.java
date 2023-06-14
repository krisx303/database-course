package com.group.travels.domain.booking;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group.travels.domain.customer.Customer;
import com.group.travels.domain.payments.Payment;
import com.group.travels.domain.travel.Travel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter(AccessLevel.PACKAGE)
@Builder
@Table(name = "BOOKINGS")
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    @JsonBackReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "TRAVEL_ID")
    @JsonBackReference
    private Travel travel;

    @Column(name = "BOOKING_DATE")
    private LocalDateTime bookingDate;

    @OneToOne
    @JsonBackReference
    private Payment payment;

    @Enumerated(EnumType.STRING)
    @Column(name = "BOOKING_STATE")
    private BookingState bookingState;

    double getSavingsValue() {
        if(payment == null) return 0;
        if(payment.getDiscount() == null) return 0;
        return payment.getDiscount().calculateSavings(travel.getPrice());
    }
}
