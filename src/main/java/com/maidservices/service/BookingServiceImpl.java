package com.maidservices.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maidservices.dtos.BookingDTO;
import com.maidservices.exceptions.ResourceNotFoundException;
import com.maidservices.models.Booking;
import com.maidservices.models.Maid;
import com.maidservices.models.Owner;
import com.maidservices.repo.BookingRepo;
import com.maidservices.repo.MaidRepo;
import com.maidservices.repo.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OwnerRepo ownerRepo;

    @Autowired
    private MaidRepo maidRepo;

    @Override
    public BookingDTO BookMaidById(BookingDTO bookingDTO) {
        Booking booking = objectMapper.convertValue(bookingDTO , Booking.class);
        // Set Owner and Maid in Booking
        Owner owner = ownerRepo.findById(bookingDTO.getOwner().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found"));

        Maid maid = maidRepo.findById(bookingDTO.getMaid().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Maid not found"));
        booking.setOwner(owner);
        booking.setMaid(maid);
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setBookingTime(bookingDTO.getBookingTime());
        booking.setBookingLocation(bookingDTO.getBookingLocation());
        booking.setBookingStatus(bookingDTO.getBookingStatus());

        // Save booking
        Booking saved = bookingRepo.save(booking);

        // Convert back to DTO
        return objectMapper.convertValue(saved, BookingDTO.class);
    }

        @Override
    public BookingDTO cancelBooking(Long id) {
        return null;
    }

    @Override
    public BookingDTO updateBooking(Long id) {
        return null;
    }
}
