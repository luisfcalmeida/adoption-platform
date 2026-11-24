package io.github.luisfcalmeida.adoptionplatform.entity;

import io.github.luisfcalmeida.adoptionplatform.enums.ApplicationStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "adoption_application")
public class AdoptionApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @Column(name = "applicant_name",  nullable = false)
    private String applicantName;

    @Column(name = "applicant_email", nullable = false)
    private String applicantEmail;

    @Column(name = "applicant_phone")
    private String applicantPhone;

    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status = ApplicationStatus.PENDING;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public AdoptionApplication() {
    }

    public AdoptionApplication(Animal animal, String applicantName, String applicantEmail, String applicantPhone, String message) {
        this.animal = animal;
        this.applicantName = applicantName;
        this.applicantEmail = applicantEmail;
        this.applicantPhone = applicantPhone;
        this.message = message;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getApplicantPhone() {
        return applicantPhone;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Long getId() {
        return id;
    }

    public void setStatus(ApplicationStatus applicationStatus) {
        this.status = applicationStatus;
    }
}