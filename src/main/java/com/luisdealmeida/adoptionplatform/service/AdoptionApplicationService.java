package com.luisdealmeida.adoptionplatform.service;

import com.luisdealmeida.adoptionplatform.dto.ApplicationDto;
import com.luisdealmeida.adoptionplatform.dto.ApplicationRequest;
import com.luisdealmeida.adoptionplatform.entity.AdoptionApplication;
import com.luisdealmeida.adoptionplatform.entity.Animal;
import com.luisdealmeida.adoptionplatform.entity.enums.AdoptionStatus;
import com.luisdealmeida.adoptionplatform.entity.enums.ApplicationStatus;
import com.luisdealmeida.adoptionplatform.exception.AnimalNotAvailableException;
import com.luisdealmeida.adoptionplatform.exception.ResourceNotFoundException;
import com.luisdealmeida.adoptionplatform.repository.AdoptionApplicationRepository;
import com.luisdealmeida.adoptionplatform.repository.AdoptionApplicationSpecifications;
import com.luisdealmeida.adoptionplatform.repository.AnimalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AdoptionApplicationService {

    private final AdoptionApplicationRepository adoptionApplicationRepository;
    private final AnimalRepository animalRepository;

    public AdoptionApplicationService(AdoptionApplicationRepository adoptionApplicationRepository,
                                       AnimalRepository animalRepository) {
        this.adoptionApplicationRepository = adoptionApplicationRepository;
        this.animalRepository = animalRepository;
    }

    public ApplicationDto submit(Long animalId, ApplicationRequest request) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found: " + animalId));

        if (animal.getAdoptionStatus() != AdoptionStatus.AVAILABLE) {
            throw new AnimalNotAvailableException("Animal " + animalId + " is not available for adoption");
        }

        AdoptionApplication application = new AdoptionApplication(
                animal,
                request.getApplicantName(),
                request.getApplicantEmail(),
                request.getApplicantPhone(),
                request.getApplicantAddress(),
                request.getMessage()
        );

        AdoptionApplication saved = adoptionApplicationRepository.save(application);
        return toDto(saved);
    }

    public ApplicationDto findById(Long id) {
        AdoptionApplication application = adoptionApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found: " + id));
        return toDto(application);
    }

    public ApplicationDto updateStatus(Long id, ApplicationStatus status) {
        AdoptionApplication application = adoptionApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found: " + id));
        application.setStatus(status);
        AdoptionApplication saved = adoptionApplicationRepository.save(application);
        return toDto(saved);
    }

    public Page<ApplicationDto> findAllPaginatedAndFiltered(ApplicationStatus status, Long animalId, Pageable pageable) {
        Specification<AdoptionApplication> spec = AdoptionApplicationSpecifications.hasStatus(status)
                .and(AdoptionApplicationSpecifications.hasAnimalId(animalId));

        return adoptionApplicationRepository.findAll(spec, pageable)
                .map(this::toDto);
    }

    private ApplicationDto toDto(AdoptionApplication application) {
        return new ApplicationDto(
                application.getId(),
                application.getAnimal().getId(),
                application.getAnimal().getName(),
                application.getApplicantName(),
                application.getApplicantEmail(),
                application.getApplicantPhone(),
                application.getApplicantAddress(),
                application.getMessage(),
                application.getStatus(),
                application.getSubmittedAt()
        );
    }
}
