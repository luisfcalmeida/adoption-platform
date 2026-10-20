package io.github.luisfcalmeida.adoptionplatform.service;

import io.github.luisfcalmeida.adoptionplatform.dto.CreateAnimalRequest;
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

    public Animal create(CreateAnimalRequest createAnimalRequest) {
        Animal animal = new Animal(
                createAnimalRequest.name(),
                createAnimalRequest.description(),
                createAnimalRequest.photoUrl(),
                createAnimalRequest.species(),
                createAnimalRequest.size(),
                createAnimalRequest.sex(),
                createAnimalRequest.birthDate()
        );
        return animalRepository.save(animal);
    }

    public Optional<Animal> update(Long id, UpdateAnimalRequest updateAnimalRequest) {
        Optional<Animal> optionalAnimal = animalRepository.findById(id);

        if (optionalAnimal.isPresent()) {
            Animal animal = optionalAnimal.get();
            animal.setName(updateAnimalRequest.name());
            animal.setDescription(updateAnimalRequest.description());
            animal.setPhotoUrl(updateAnimalRequest.photoUrl());
            animal.setSpecies(updateAnimalRequest.species());
            animal.setSize(updateAnimalRequest.size());
            animal.setSex(updateAnimalRequest.sex());
            animal.setBirthDate(updateAnimalRequest.birthDate());
            return Optional.of(animalRepository.save(animal));
        } else {
            return Optional.empty();
        }
    }
}
