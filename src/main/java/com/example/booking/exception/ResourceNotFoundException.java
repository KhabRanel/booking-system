package com.example.booking.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class ResourceNotFoundException extends BookingException {

    public ResourceNotFoundException(UUID id) {
        super("Resource not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}
