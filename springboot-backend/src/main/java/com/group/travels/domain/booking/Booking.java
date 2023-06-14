package com.group.travels.domain.booking;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group.travels.domain.customer.Customer;
import com.group.travels.domain.payments.Payment;
import com.group.travels.domain.travel.Travel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    //bookings
    @OneToMany(mappedBy = "booking")
    @JsonManagedReference
    @Builder.Default
    private List<Payment> payments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "BOOKING_STATE")
    private BookingState bookingState;
}
