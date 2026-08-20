package com.luisdealmeida.adoptionplatform.controller;

import com.luisdealmeida.adoptionplatform.dto.AnimalDto;
import com.luisdealmeida.adoptionplatform.dto.AnimalPhotoDto;
import com.luisdealmeida.adoptionplatform.dto.AnimalRequest;
import com.luisdealmeida.adoptionplatform.entity.enums.AdoptionStatus;
import com.luisdealmeida.adoptionplatform.entity.enums.Sex;
import com.luisdealmeida.adoptionplatform.entity.enums.Size;
import com.luisdealmeida.adoptionplatform.entity.enums.Species;
import com.luisdealmeida.adoptionplatform.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/api/animals")
    public ResponseEntity<Page<AnimalDto>> findAvailable(
            @RequestParam(required = false) Species species,
            @RequestParam(required = false) Sex sex,
            @RequestParam(required = false, name = "animalSize") Size size,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            Pageable pageable) {

        Page<AnimalDto> animals = animalService.findAvailablePaginatedAndFiltered(
                pageable, species, sex, size, minAge, maxAge);
        return ResponseEntity.ok(animals);
    }

    @GetMapping("/api/animals/{id}")
    public ResponseEntity<AnimalDto> findById(@PathVariable Long id) {
        AnimalDto animal = animalService.findById(id);
        return ResponseEntity.ok(animal);
    }

    @GetMapping("/api/staff/animals")
    public ResponseEntity<Page<AnimalDto>> findAll(
            @RequestParam(required = false) Species species,
            @RequestParam(required = false) Sex sex,
            @RequestParam(required = false, name = "animalSize") Size size,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            Pageable pageable) {

        Page<AnimalDto> animals = animalService.findAllPaginatedAndFiltered(
                pageable, species, sex, size, minAge, maxAge);
        return ResponseEntity.ok(animals);
    }

    @PostMapping("/api/animals")
    public ResponseEntity<AnimalDto> create(@Valid @RequestBody AnimalRequest request) {
        AnimalDto created = animalService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/api/animals/{id}")
    public ResponseEntity<AnimalDto> update(@PathVariable Long id, @Valid @RequestBody AnimalRequest request) {
        AnimalDto updated = animalService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/api/animals/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        animalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/api/animals/{id}/status")
    public ResponseEntity<AnimalDto> updateStatus(@PathVariable Long id, @RequestParam AdoptionStatus adoptionStatus) {
        AnimalDto updated = animalService.updateStatus(id, adoptionStatus);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/api/animals/{id}/photos")
    public ResponseEntity<AnimalPhotoDto> uploadPhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        AnimalPhotoDto photo = animalService.uploadPhoto(id, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(photo);
    }

    @DeleteMapping("/api/animals/{id}/photos/{photoId}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long id, @PathVariable Long photoId) {
        animalService.deletePhoto(id, photoId);
        return ResponseEntity.noContent().build();
    }
}
