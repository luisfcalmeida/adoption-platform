package com.luisdealmeida.adoptionplatform.controller;

import com.luisdealmeida.adoptionplatform.dto.ApplicationDto;
import com.luisdealmeida.adoptionplatform.dto.ApplicationRequest;
import com.luisdealmeida.adoptionplatform.entity.enums.ApplicationStatus;
import com.luisdealmeida.adoptionplatform.service.AdoptionApplicationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdoptionApplicationController {

    private final AdoptionApplicationService adoptionApplicationService;

    public AdoptionApplicationController(AdoptionApplicationService adoptionApplicationService) {
        this.adoptionApplicationService = adoptionApplicationService;
    }

    @PostMapping("/api/animals/{animalId}/applications")
    public ResponseEntity<ApplicationDto> submit(@PathVariable Long animalId, @Valid @RequestBody ApplicationRequest request) {
        ApplicationDto created = adoptionApplicationService.submit(animalId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/api/applications")
    public ResponseEntity<Page<ApplicationDto>> findAll(
            @RequestParam(required = false) ApplicationStatus status,
            @RequestParam(required = false) Long animalId,
            Pageable pageable) {
        Page<ApplicationDto> applications = adoptionApplicationService.findAllPaginatedAndFiltered(status, animalId, pageable);
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/api/applications/{id}")
    public ResponseEntity<ApplicationDto> findById(@PathVariable Long id) {
        ApplicationDto application = adoptionApplicationService.findById(id);
        return ResponseEntity.ok(application);
    }

    @PatchMapping("/api/applications/{id}/status")
    public ResponseEntity<ApplicationDto> updateStatus(@PathVariable Long id, @RequestParam ApplicationStatus status) {
        ApplicationDto updated = adoptionApplicationService.updateStatus(id, status);
        return ResponseEntity.ok(updated);
    }
}
