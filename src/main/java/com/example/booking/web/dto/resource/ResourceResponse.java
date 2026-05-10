package com.example.booking.web.dto.resource;

import com.example.booking.domain.enums.ResourceStatus;

import java.time.Instant;
import java.util.UUID;

public record ResourceResponse(
        UUID id,
        String name,
        String description,
        int capacity,
        ResourceStatus status,
        Instant createdAt
) {
}
