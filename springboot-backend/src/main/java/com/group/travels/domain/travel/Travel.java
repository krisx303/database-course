package com.group.travels.domain.travel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group.travels.domain.booking.Booking;
//import com.group.travels.domain.country.Country;
import com.group.travels.domain.country.Country;
import com.group.travels.domain.customer.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter(AccessLevel.PACKAGE)
@Builder
@Table(name = "TRAVELS")
@NoArgsConstructor
@AllArgsConstructor
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TRAVEL_NAME")
    private String travelName;

    @Column(name = "TRAVEL_DATE")
    private LocalDateTime travelDate;

    @Column(name = "MAX_NUMBER_OF_PLACES")
    private Integer maxNumberOfPlaces;

    @Column(name = "NUMBER_OF_FREE_PLACES")
    private Integer numberOfFreePlaces;

    @Column(name="PRICE")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "COUNTRY")
    @JsonBackReference
    private Country country;

    @OneToMany(mappedBy = "travel")
    @JsonManagedReference
    @Builder.Default
    private List<Booking> bookings = new ArrayList<>();
}
