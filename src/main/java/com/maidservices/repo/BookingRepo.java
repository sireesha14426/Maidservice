package com.maidservices.repo;

import com.maidservices.models.Booking;
import com.maidservices.models.Maid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking,Long> {
    boolean existsByMaidAndBookingDateAndBookingTime(Maid maid, String bookingDate, String bookingTime);
}
