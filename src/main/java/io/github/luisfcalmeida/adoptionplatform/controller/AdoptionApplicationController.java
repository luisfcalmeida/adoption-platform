package io.github.luisfcalmeida.adoptionplatform.controller;

import io.github.luisfcalmeida.adoptionplatform.dto.ApplicationResponse;
import io.github.luisfcalmeida.adoptionplatform.dto.CreateApplicationRequest;
import io.github.luisfcalmeida.adoptionplatform.entity.AdoptionApplication;
import io.github.luisfcalmeida.adoptionplatform.service.AdoptionApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/animals/{animalId}/applications")
public class AdoptionApplicationController {

    private final AdoptionApplicationService adoptionApplicationService;

    public AdoptionApplicationController(AdoptionApplicationService adoptionApplicationService) {
        this.adoptionApplicationService = adoptionApplicationService;
    }

    @PostMapping
    public ResponseEntity<ApplicationResponse> submitApplication(@PathVariable Long animalId, @Valid @RequestBody CreateApplicationRequest request) {
        AdoptionApplication submittedApplication = adoptionApplicationService.submit(request, animalId);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApplicationResponse.from(submittedApplication));
    }

}