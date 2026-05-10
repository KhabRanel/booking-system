package com.example.booking.exception;

import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.UUID;

public class SlotNotAvailableException extends BookingException {

    public SlotNotAvailableException(UUID resourceId, Instant startTime, Instant endTime) {
        super("Resource " + resourceId + " is not available from " + startTime + " to " + endTime,
                HttpStatus.CONFLICT);
    }
}
