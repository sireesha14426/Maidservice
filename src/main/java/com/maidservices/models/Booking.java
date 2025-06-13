package com.maidservices.models;

import com.maidservices.constants.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
    @Table(
            name = "bookings"
           /* uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"maid_id", "booking_date", "booking_time"})
            }*/
    )
    public class Booking {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "booking_id")
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY, optional = false) // Many bookings to one owner
        @JoinColumn(name = "owner_id", nullable = false) // Foreign key column in bookings table
        private Owner owner; // Reference to the Owner entity

        @ManyToOne(fetch = FetchType.LAZY, optional = false) // Many bookings to one maid
        @JoinColumn(name = "maid_id", nullable = false) // Foreign key column in bookings table
        private Maid maid; // Reference to the Maid entity

        @Column(name = "booking_date", nullable = false)
        private String bookingDate;

        @Column(name = "booking_time", nullable = false)
        private String bookingTime;

        @Column(nullable = false)
        private String bookingLocation;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private BookingStatus bookingStatus;
    }
