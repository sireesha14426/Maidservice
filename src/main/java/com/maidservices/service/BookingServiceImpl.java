package com.maidservices.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maidservices.constants.BookingStatus;
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
        boolean maidAlreadyBooked = bookingRepo.existsByMaidAndBookingDateAndBookingTime(
                maid,
                bookingDTO.getBookingDate(),
                bookingDTO.getBookingTime()
        );

        if (maidAlreadyBooked) {
            throw new IllegalStateException("Maid is not available at the selected date and time.");
        }
        booking.setOwner(owner);
        booking.setMaid(maid);
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setBookingTime(bookingDTO.getBookingTime());
        booking.setBookingLocation(bookingDTO.getBookingLocation());
        booking.setBookingStatus(BookingStatus.PENDING);

        // Save booking
        Booking saved = bookingRepo.save(booking);

        // Convert back to DTO
        return objectMapper.convertValue(saved, BookingDTO.class);
    }

        @Override
        public BookingDTO cancelBooking(Long id) {
            Booking booking = bookingRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));

            // Get the current status for messaging
            BookingStatus currentStatus = booking.getBookingStatus();

            if (currentStatus == BookingStatus.COMPLETED) {
                throw new IllegalStateException("Booking with id " + id + " cannot be cancelled as it is already COMPLETED. Current status: " + currentStatus);
            } else if (currentStatus == BookingStatus.CANCELLED) {
                throw new IllegalStateException("Booking with id " + id + " is already CANCELLED. Current status: " + currentStatus);
            }

            // Only allow cancellation if status is PENDING or CONFIRMED
            if (currentStatus == BookingStatus.PENDING || currentStatus == BookingStatus.CONFIRMED) {
                booking.setBookingStatus(BookingStatus.CANCELLED); // Set to cancelled
                Booking updatedBooking = bookingRepo.save(booking);
                return objectMapper.convertValue(updatedBooking, BookingDTO.class);
            } else {
                throw new IllegalStateException("Booking with id " + id + " was cancel successfully" + currentStatus);
            }
        }

    @Override
    public BookingDTO updateBooking(Long id) {
        return null;
    }
}
