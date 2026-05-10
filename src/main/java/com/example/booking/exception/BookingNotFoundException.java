package com.example.booking.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class BookingNotFoundException extends BookingException {

    public BookingNotFoundException(UUID id) {
        super("Booking not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}
