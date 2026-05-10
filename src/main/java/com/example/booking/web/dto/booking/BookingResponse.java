package com.example.booking.web.dto.booking;

import com.example.booking.domain.enums.BookingStatus;

import java.time.Instant;
import java.util.UUID;

public record BookingResponse(
        UUID id,
        UUID userId,
        UUID resourceId,
        String resourceName,
        Instant startTime,
        Instant endTime,
        BookingStatus status,
        Instant createdAt
) {
}
