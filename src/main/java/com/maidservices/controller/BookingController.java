package com.maidservices.controller;

import com.maidservices.constants.BookingStatus;
import com.maidservices.dtos.BookingDTO;
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
    public ResponseEntity<BookingDTO> cancelBooking(@PathVariable Long id, BookingStatus bookingStatus){
        BookingDTO bookingDTO=bookingService.cancelBooking(id,bookingStatus);
        return ResponseEntity.ok(bookingDTO);
    }
}
