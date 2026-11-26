package io.github.luisfcalmeida.adoptionplatform.service;

import io.github.luisfcalmeida.adoptionplatform.dto.CreateApplicationRequest;
import io.github.luisfcalmeida.adoptionplatform.entity.AdoptionApplication;
import io.github.luisfcalmeida.adoptionplatform.entity.Animal;
import io.github.luisfcalmeida.adoptionplatform.enums.AdoptionStatus;
import io.github.luisfcalmeida.adoptionplatform.exception.BusinessRuleException;
import io.github.luisfcalmeida.adoptionplatform.exception.ResourceNotFoundException;
import io.github.luisfcalmeida.adoptionplatform.repository.AdoptionApplicationRepository;
import io.github.luisfcalmeida.adoptionplatform.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdoptionApplicationService {

    private final AdoptionApplicationRepository adoptionApplicationRepository;
    private final AnimalRepository animalRepository;

    public AdoptionApplicationService(AdoptionApplicationRepository adoptionApplicationRepository, AnimalRepository animalRepository) {
        this.adoptionApplicationRepository = adoptionApplicationRepository;
        this.animalRepository = animalRepository;
    }

    public AdoptionApplication submit(CreateApplicationRequest request, Long animalId) {
        Optional<Animal> optionalAnimal = animalRepository.findById(animalId);

        if (optionalAnimal.isEmpty()) {
            throw new ResourceNotFoundException("Animal not found with id " + animalId);
        }

        Animal animal = optionalAnimal.get();

        if (animal.getAdoptionStatus() != AdoptionStatus.AVAILABLE) {
            throw new BusinessRuleException("Animal with id " + animalId + " is not available for adoption.");
        }

        AdoptionApplication adoptionApplication = new AdoptionApplication(
                animal,
                request.applicantName(),
                request.applicantEmail(),
                request.applicantPhone(),
                request.message()
        );

        return adoptionApplicationRepository.save(adoptionApplication);
    }

}