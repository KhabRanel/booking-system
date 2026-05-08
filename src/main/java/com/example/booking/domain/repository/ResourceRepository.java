package com.example.booking.domain.repository;

import com.example.booking.domain.entity.Resource;
import com.example.booking.domain.enums.ResourceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, UUID> {

    List<Resource> findAllByStatus(ResourceStatus status);
}
