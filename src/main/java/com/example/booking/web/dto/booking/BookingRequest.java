package com.example.booking.web.dto.booking;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

public record BookingRequest(

        @NotNull(message = "Resource id is required")
        UUID resourceId,

        @NotNull(message = "Start time is required")
        @Future(message = "Start time must be in the future")
        Instant startTime,

        @NotNull(message = "End time is required")
        @Future(message = "Start time must be in the future")
        Instant endTime
) {
}
