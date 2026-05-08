package com.example.booking.domain.repository;

import com.example.booking.domain.entity.Booking;
import com.example.booking.domain.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    List<Booking> findAllByUserId(UUID userId);


    @Query("""
            SELECT COUNT(b) > 0 FROM Booking b
            WHERE b.resource.id = :resourceId
            AND b.status NOT IN ('CANCELED', 'EXPIRED')
            AND b.startTime < :endTime
            AND b.endTime > :startTime
            """)
    boolean existOverlappingBooking(
            @Param("resourceId") UUID resourceId,
            @Param("startTime") Instant startTime,
            @Param("endTime") Instant endTime
    );

    List<Booking> findAllByStatusAndCreatedAtBefore(BookingStatus status, Instant createdAt);
}
