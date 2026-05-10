package com.example.booking.web.dto.resource;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ResourceRequest(

        @NotBlank(message = "Name is required")
        String name,

        String description,

        @Min(value = 1, message = "Capacity must be at least 1")
        int capacity
) {
}
