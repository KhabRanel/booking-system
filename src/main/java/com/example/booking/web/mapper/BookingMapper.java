package com.example.booking.web.mapper;

import com.example.booking.domain.entity.Booking;
import com.example.booking.web.dto.booking.BookingResponse;

public class BookingMapper {

    public BookingResponse toResponse(Booking booking) {
        return new BookingResponse(
                booking.getId(),
                booking.getUser().getId(),
                booking.getResource().getId(),
                booking.getResource().getName(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getStatus(),
                booking.getCreatedAt()
        );
    }
}
