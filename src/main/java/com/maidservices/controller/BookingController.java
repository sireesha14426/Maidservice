package com.maidservices.controller;

import com.maidservices.constants.BookingStatus;
import com.maidservices.dtos.BookingDTO;
import com.maidservices.exceptions.ResourceNotFoundException;
import com.maidservices.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookingservice")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<BookingDTO> BookMaidById(@RequestBody BookingDTO bookingDTO) {
        BookingDTO booked = bookingService.BookMaidById(bookingDTO);
        return new ResponseEntity<>(booked, HttpStatus.CREATED);
    }

    @PutMapping("/cancelBooking/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        try {
            BookingDTO cancelledBooking = bookingService.cancelBooking(id);
            return new ResponseEntity<>("Booking with ID " + id + " successfully cancelled.", HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalStateException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT); // 409 Conflict (e.g., already completed/cancelled)
        } catch (Exception ex) {
            return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }
}
