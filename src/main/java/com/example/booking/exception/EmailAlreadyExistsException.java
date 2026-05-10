package com.example.booking.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends BookingException {

    public EmailAlreadyExistsException(String email) {
        super("User with email " + email + " already exists", HttpStatus.CONFLICT);
    }
}
