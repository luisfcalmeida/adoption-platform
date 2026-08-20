package com.luisdealmeida.adoptionplatform.dto;

import com.luisdealmeida.adoptionplatform.entity.enums.AdoptionStatus;
import com.luisdealmeida.adoptionplatform.entity.enums.Sex;
import com.luisdealmeida.adoptionplatform.entity.enums.Size;
import com.luisdealmeida.adoptionplatform.entity.enums.Species;

import java.time.LocalDateTime;

public class AnimalDto {

    private final Long id;
    private final String name;
    private final Species species;
    private final String breed;
    private final Sex sex;
    private final Size size;
    private final int ageYears;
    private final String description;
    private final AdoptionStatus adoptionStatus;
    private final LocalDateTime createdAt;

    public AnimalDto(Long id, String name, Species species, String breed, Sex sex, Size size, int ageYears, String description, AdoptionStatus adoptionStatus, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.sex = sex;
        this.size = size;
        this.ageYears = ageYears;
        this.description = description;
        this.adoptionStatus = adoptionStatus;
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Species getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public Sex getSex() {
        return sex;
    }

    public Size getSize() {
        return size;
    }

    public int getAgeYears() {
        return ageYears;
    }

    public AdoptionStatus getAdoptionStatus() {
        return adoptionStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}




