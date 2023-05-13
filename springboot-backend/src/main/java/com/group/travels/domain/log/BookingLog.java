package com.group.travels.domain.log;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.group.travels.domain.booking.Booking;
import com.group.travels.domain.booking.BookingState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter(AccessLevel.PACKAGE)
@Builder
@Table(name = "LOGS")
@NoArgsConstructor
@AllArgsConstructor
public class BookingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "BOOKING_ID")
    @JsonBackReference
    private Booking booking;

    @Column(name = "LOG_DATE")
    private LocalDateTime bookingDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "LOG_STATE")
    private BookingState bookingState;
}
