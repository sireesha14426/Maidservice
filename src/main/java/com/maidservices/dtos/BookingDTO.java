package com.maidservices.dtos;

import com.maidservices.constants.BookingStatus;
import com.maidservices.models.Maid;
import com.maidservices.models.Owner;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
public class BookingDTO {
    private Long id;
    private Owner owner; // Reference to the Owner entity
    private Maid maid; // Reference to the Maid entity
    private String bookingDate;
    private String bookingTime;
    private String bookingLocation;
    private BookingStatus bookingStatus;
}
