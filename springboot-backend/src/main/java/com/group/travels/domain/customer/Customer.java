package com.group.travels.domain.customer;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group.travels.domain.booking.Booking;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter(AccessLevel.PACKAGE)
@Builder
@Table(name = "CUSTOMERS")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customer_id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_email", unique = true)
    private String customerEmail;

    @Column(name = "customer_phone")
    private String customerPhone;

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<Booking> bookings;
}
