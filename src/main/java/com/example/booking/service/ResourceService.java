package com.example.booking.service;

import com.example.booking.domain.entity.Resource;
import com.example.booking.domain.enums.ResourceStatus;
import com.example.booking.domain.repository.ResourceRepository;
import com.example.booking.exception.ResourceNotFoundException;
import com.example.booking.web.dto.resource.ResourceRequest;
import com.example.booking.web.dto.resource.ResourceResponse;
import com.example.booking.web.mapper.ResourceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;

    public List<ResourceResponse> getAllActive() {
        return resourceRepository.findAllByStatus(ResourceStatus.ACTIVE)
                .stream()
                .map(resourceMapper::toResponse)
                .toList();
    }

    public ResourceResponse getById(UUID id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return resourceMapper.toResponse(resource);
    }

    public ResourceResponse create(ResourceRequest request) {
        Resource resource = resourceMapper.toEntity(request);
        return resourceMapper.toResponse(resourceRepository.save(resource));
    }

    public ResourceResponse update(UUID id, ResourceRequest request) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        resource.setName(request.name());
        resource.setDescription(request.description());
        resource.setCapacity(request.capacity());

        return resourceMapper.toResponse(resourceRepository.save(resource));
    }

    public void setStatus(UUID id, ResourceStatus status) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        resource.setStatus(status);
        resourceRepository.save(resource);
    }
}
