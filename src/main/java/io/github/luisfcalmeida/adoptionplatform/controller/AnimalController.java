package io.github.luisfcalmeida.adoptionplatform.controller;

import io.github.luisfcalmeida.adoptionplatform.dto.CreateAnimalRequest;
import io.github.luisfcalmeida.adoptionplatform.dto.UpdateAnimalRequest;
import io.github.luisfcalmeida.adoptionplatform.entity.Animal;
import io.github.luisfcalmeida.adoptionplatform.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<Animal> getAnimals() {
        return animalService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        Optional<Animal> optionalAnimal = animalService.findById(id);
        if (optionalAnimal.isPresent()) {
            return ResponseEntity.ok(optionalAnimal.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Animal> createAnimal(@Valid @RequestBody CreateAnimalRequest request) {
        Animal createdAnimal = animalService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnimal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable Long id, @Valid @RequestBody UpdateAnimalRequest request) {
        Optional<Animal> optionalAnimal = animalService.update(id, request);
        if (optionalAnimal.isPresent()) {
            return ResponseEntity.ok(optionalAnimal.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
