package com.luisdealmeida.adoptionplatform.service;

import com.luisdealmeida.adoptionplatform.dto.AnimalDto;
import com.luisdealmeida.adoptionplatform.dto.AnimalPhotoDto;
import com.luisdealmeida.adoptionplatform.dto.AnimalRequest;
import com.luisdealmeida.adoptionplatform.entity.Animal;
import com.luisdealmeida.adoptionplatform.entity.AnimalPhoto;
import com.luisdealmeida.adoptionplatform.entity.enums.AdoptionStatus;
import com.luisdealmeida.adoptionplatform.entity.enums.Sex;
import com.luisdealmeida.adoptionplatform.entity.enums.Size;
import com.luisdealmeida.adoptionplatform.entity.enums.Species;
import com.luisdealmeida.adoptionplatform.repository.AnimalPhotoRepository;
import com.luisdealmeida.adoptionplatform.repository.AnimalRepository;
import com.luisdealmeida.adoptionplatform.repository.AnimalSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalPhotoRepository animalPhotoRepository;
    private final FileStorageService fileStorageService;

    public AnimalService(AnimalRepository animalRepository,
                          AnimalPhotoRepository animalPhotoRepository,
                          FileStorageService fileStorageService) {
        this.animalRepository = animalRepository;
        this.animalPhotoRepository = animalPhotoRepository;
        this.fileStorageService = fileStorageService;
    }

    public AnimalDto create(AnimalRequest request) {
        Animal animal = new Animal(
                request.getName(),
                request.getSpecies(),
                request.getBreed(),
                request.getSex(),
                request.getSize(),
                request.getAgeYears(),
                request.getDescription()
        );
        Animal saved = animalRepository.save(animal);
        return toDto(saved);
    }

    private AnimalDto toDto(Animal animal) {
        return new AnimalDto(
                animal.getId(),
                animal.getName(),
                animal.getSpecies(),
                animal.getBreed(),
                animal.getSex(),
                animal.getSize(),
                animal.getAgeYears(),
                animal.getDescription(),
                animal.getAdoptionStatus(),
                animal.getCreatedAt()
        );
    }

    public AnimalDto findById(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Animal not found: " + id));
        return toDto(animal);
    }

    public AnimalDto update(Long id, AnimalRequest request) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Animal not found: " + id));

        animal.setName(request.getName());
        animal.setSpecies(request.getSpecies());
        animal.setBreed(request.getBreed());
        animal.setSex(request.getSex());
        animal.setSize(request.getSize());
        animal.setAgeYears(request.getAgeYears());
        animal.setDescription(request.getDescription());

        Animal saved = animalRepository.save(animal);
        return toDto(saved);
    }

    public void delete(Long id) {
        animalRepository.deleteById(id);
    }

    public AnimalDto updateStatus(Long id, AdoptionStatus adoptionStatus) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Animal not found: " + id));
        animal.setAdoptionStatus(adoptionStatus);
        Animal saved = animalRepository.save(animal);
        return toDto(saved);
    }

    public AnimalPhotoDto uploadPhoto(Long animalId, MultipartFile file) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new IllegalArgumentException("Animal not found: " + animalId));

        String fileName = fileStorageService.store(file);
        AnimalPhoto photo = new AnimalPhoto(animal, fileName);
        AnimalPhoto saved = animalPhotoRepository.save(photo);

        return toPhotoDto(saved);
    }

    public void deletePhoto(Long animalId, Long photoId) {
        AnimalPhoto photo = animalPhotoRepository.findById(photoId)
                .orElseThrow(() -> new IllegalArgumentException("Photo not found: " + photoId));

        if (!photo.getAnimal().getId().equals(animalId)) {
            throw new IllegalArgumentException("Photo " + photoId + " does not belong to animal " + animalId);
        }

        fileStorageService.delete(photo.getFileName());
        animalPhotoRepository.delete(photo);
    }

    private AnimalPhotoDto toPhotoDto(AnimalPhoto photo) {
        return new AnimalPhotoDto(photo.getId(), photo.getFileName(), photo.getUploadedAt());
    }

    public Page<AnimalDto> findAvailablePaginatedAndFiltered(Pageable pageable, Species species, Sex sex, Size size, Integer minAge, Integer maxAge) {
        Specification<Animal> spec = AnimalSpecifications.hasAdoptionStatus(AdoptionStatus.AVAILABLE)
                .and(AnimalSpecifications.hasSpecies(species))
                .and(AnimalSpecifications.hasSex(sex))
                .and(AnimalSpecifications.hasSize(size))
                .and(AnimalSpecifications.ageBetween(minAge, maxAge));

        return animalRepository.findAll(spec, pageable)
                .map(this::toDto);
    }

    public Page<AnimalDto> findAllPaginatedAndFiltered(Pageable pageable, Species species, Sex sex, Size size, Integer minAge, Integer maxAge) {
        Specification<Animal> spec = AnimalSpecifications.hasSpecies(species)
                .and(AnimalSpecifications.hasSex(sex))
                .and(AnimalSpecifications.hasSize(size))
                .and(AnimalSpecifications.ageBetween(minAge, maxAge));

        return animalRepository.findAll(spec, pageable)
                .map(this::toDto);
    }

}
