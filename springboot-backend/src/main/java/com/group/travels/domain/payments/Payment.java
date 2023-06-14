package com.group.travels.domain.payments;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.group.travels.domain.booking.Booking;
import com.group.travels.domain.customer.Customer;
import com.group.travels.domain.discount.Discount;
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
@Table(name = "PAYMENTS")
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //booking
    @ManyToOne
    @JoinColumn(name = "BOOKING_ID")
    @JsonBackReference
    private Booking booking;


    //date
    @Column(name = "PAYMENT_DATE")
    private LocalDateTime paymentDate;

    //price
    @Column(name = "PRICE")
    private Integer price;

    //discount
    //one to one discount
    @OneToOne
    @JoinColumn(name = "DISCOUNT_ID")
    private Discount discount;
}
