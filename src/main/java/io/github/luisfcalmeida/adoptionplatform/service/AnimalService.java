package io.github.luisfcalmeida.adoptionplatform.service;

import io.github.luisfcalmeida.adoptionplatform.dto.CreateAnimalRequest;
import io.github.luisfcalmeida.adoptionplatform.dto.UpdateAdoptionStatusRequest;
import io.github.luisfcalmeida.adoptionplatform.dto.UpdateAnimalRequest;
import io.github.luisfcalmeida.adoptionplatform.entity.Animal;
import io.github.luisfcalmeida.adoptionplatform.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    public Optional<Animal> findById(Long id) {
        return animalRepository.findById(id);
    }

    public Animal create(CreateAnimalRequest request) {
        Animal animal = new Animal(
                request.name(),
                request.description(),
                request.photoUrl(),
                request.species(),
                request.size(),
                request.sex(),
                request.birthDate()
        );
        return animalRepository.save(animal);
    }

    public Optional<Animal> update(Long id, UpdateAnimalRequest request) {
        Optional<Animal> optionalAnimal = animalRepository.findById(id);

        if (optionalAnimal.isPresent()) {
            Animal animal = optionalAnimal.get();
            animal.setName(request.name());
            animal.setDescription(request.description());
            animal.setPhotoUrl(request.photoUrl());
            animal.setSpecies(request.species());
            animal.setSize(request.size());
            animal.setSex(request.sex());
            animal.setBirthDate(request.birthDate());
            return Optional.of(animalRepository.save(animal));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Animal> updateAdoptionStatus(Long id, UpdateAdoptionStatusRequest request) {
        Optional<Animal> optionalAnimal = animalRepository.findById(id);

        if (optionalAnimal.isPresent()) {
            Animal animal = optionalAnimal.get();
            animal.setAdoptionStatus(request.adoptionStatus());
            return Optional.of(animalRepository.save(animal));
        } else {
            return Optional.empty();
        }
    }
}
