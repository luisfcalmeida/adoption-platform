package com.luisdealmeida.adoptionplatform.dto;

import com.luisdealmeida.adoptionplatform.entity.enums.ApplicationStatus;

import java.time.LocalDateTime;

public class ApplicationDto {

    private final Long id;
    private final Long animalId;
    private final String animalName;
    private final String applicantName;
    private final String applicantEmail;
    private final String applicantPhone;
    private final String applicantAddress;
    private final String message;
    private final ApplicationStatus status;
    private final LocalDateTime submittedAt;

    public ApplicationDto(Long id, Long animalId, String animalName, String applicantName, String applicantEmail,
                           String applicantPhone, String applicantAddress, String message,
                           ApplicationStatus status, LocalDateTime submittedAt) {
        this.id = id;
        this.animalId = animalId;
        this.animalName = animalName;
        this.applicantName = applicantName;
        this.applicantEmail = applicantEmail;
        this.applicantPhone = applicantPhone;
        this.applicantAddress = applicantAddress;
        this.message = message;
        this.status = status;
        this.submittedAt = submittedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public String getApplicantPhone() {
        return applicantPhone;
    }

    public String getApplicantAddress() {
        return applicantAddress;
    }

    public String getMessage() {
        return message;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }
}
