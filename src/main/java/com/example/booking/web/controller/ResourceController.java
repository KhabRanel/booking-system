package com.example.booking.web.controller;

import com.example.booking.domain.enums.ResourceStatus;
import com.example.booking.service.ResourceService;
import com.example.booking.web.dto.resource.ResourceRequest;
import com.example.booking.web.dto.resource.ResourceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping
    public List<ResourceResponse> getAllActive() {
        return resourceService.getAllActive();
    }

    @GetMapping("/{id}")
    public ResourceResponse getById(@PathVariable UUID id) {
        return resourceService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ResourceResponse create(@Valid @RequestBody ResourceRequest request) {
        return resourceService.create(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResourceResponse update(@PathVariable UUID id,
                                   @Valid @RequestBody ResourceRequest request) {
        return resourceService.update(id, request);
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setStatus(@PathVariable UUID id,
                          @RequestParam ResourceStatus status) {
        resourceService.setStatus(id, status);
    }
}
