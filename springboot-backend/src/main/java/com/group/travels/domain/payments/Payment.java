package com.group.travels.domain.payments;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.group.travels.domain.customer.Customer;
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

    //customers
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    @JsonBackReference
    private Customer customer;

    //travel
    @ManyToOne
    @JoinColumn(name = "TRAVEL_ID")
    @JsonBackReference
    private Travel travel;

    //date
    @Column(name = "PAYMENT_DATE")
    private LocalDateTime paymentDate;

    //price
    @Column(name = "PRICE")
    private Integer price;
}
