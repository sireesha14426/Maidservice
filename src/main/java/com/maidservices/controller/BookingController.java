package com.maidservices.controller;

import com.maidservices.dtos.BookingDTO;
import com.maidservices.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookingservice")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<BookingDTO> bookMaid(@RequestBody BookingDTO bookingDTO) {
        BookingDTO booked = bookingService.BookMaidById(bookingDTO);
        return new ResponseEntity<>(booked, HttpStatus.CREATED);
    }
}
