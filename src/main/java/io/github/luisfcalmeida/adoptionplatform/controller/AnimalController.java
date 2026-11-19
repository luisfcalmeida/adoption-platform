package io.github.luisfcalmeida.adoptionplatform.controller;

import io.github.luisfcalmeida.adoptionplatform.dto.AnimalResponse;
import io.github.luisfcalmeida.adoptionplatform.dto.CreateAnimalRequest;
import io.github.luisfcalmeida.adoptionplatform.dto.UpdateAdoptionStatusRequest;
import io.github.luisfcalmeida.adoptionplatform.dto.UpdateAnimalRequest;
import io.github.luisfcalmeida.adoptionplatform.entity.Animal;
import io.github.luisfcalmeida.adoptionplatform.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public List<AnimalResponse> getAnimals() {
        List<Animal> animals = animalService.findAll();
        List<AnimalResponse> animalResponses = new ArrayList<>();

        for (Animal animal : animals) {
            animalResponses.add(AnimalResponse.from(animal));
        }

        return animalResponses;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> getAnimalById(@PathVariable Long id) {
        Optional<Animal> optionalAnimal = animalService.findById(id);
        if (optionalAnimal.isPresent()) {
            return ResponseEntity.ok(AnimalResponse.from(optionalAnimal.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AnimalResponse> createAnimal(@Valid @RequestBody CreateAnimalRequest request) {
        Animal createdAnimal = animalService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(AnimalResponse.from(createdAnimal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponse> updateAnimal(@PathVariable Long id, @Valid @RequestBody UpdateAnimalRequest request) {
        Optional<Animal> optionalAnimal = animalService.update(id, request);
        if (optionalAnimal.isPresent()) {
            return ResponseEntity.ok(AnimalResponse.from(optionalAnimal.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AnimalResponse> updateAdoptionStatus(@PathVariable Long id, @Valid @RequestBody UpdateAdoptionStatusRequest request) {
        Optional<Animal> optionalAnimal = animalService.updateAdoptionStatus(id, request);
        if (optionalAnimal.isPresent()) {
            return ResponseEntity.ok(AnimalResponse.from(optionalAnimal.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        if (animalService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
