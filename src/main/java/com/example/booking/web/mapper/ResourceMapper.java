package com.example.booking.web.mapper;

import com.example.booking.domain.entity.Resource;
import com.example.booking.web.dto.resource.ResourceRequest;
import com.example.booking.web.dto.resource.ResourceResponse;
import org.springframework.stereotype.Component;

@Component
public class ResourceMapper {

    public ResourceResponse toResponse(Resource resource) {
        return new ResourceResponse(
                resource.getId(),
                resource.getName(),
                resource.getDescription(),
                resource.getCapacity(),
                resource.getStatus(),
                resource.getCreatedAt()
        );
    }

    public Resource toEntity(ResourceRequest request) {
        return Resource.builder()
                .name(request.name())
                .description(request.description())
                .capacity(request.capacity())
                .build();
    }
}
