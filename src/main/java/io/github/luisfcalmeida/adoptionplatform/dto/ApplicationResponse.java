package io.github.luisfcalmeida.adoptionplatform.dto;

import io.github.luisfcalmeida.adoptionplatform.entity.AdoptionApplication;
import io.github.luisfcalmeida.adoptionplatform.enums.ApplicationStatus;

import java.time.Instant;

public record ApplicationResponse(
        Long id,
        String applicantName,
        String applicantEmail,
        String applicantPhone,
        String message,
        ApplicationStatus status,
        Instant createdAt,
        Long animalId
) {
    public static ApplicationResponse from(AdoptionApplication adoptionApplication) {
        return new ApplicationResponse(
                adoptionApplication.getId(),
                adoptionApplication.getApplicantName(),
                adoptionApplication.getApplicantEmail(),
                adoptionApplication.getApplicantPhone(),
                adoptionApplication.getMessage(),
                adoptionApplication.getStatus(),
                adoptionApplication.getCreatedAt(),
                adoptionApplication.getAnimal().getId()
        );
    }
}
