package com.maidservices.service;

import com.maidservices.dtos.BookingDTO;
import com.maidservices.models.Booking;

public interface BookingService {
    public BookingDTO BookMaidById(BookingDTO bookingDTO);
    public BookingDTO cancelBooking(Long id);
    public BookingDTO updateBooking(Long id);
}
