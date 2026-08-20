package com.luisdealmeida.adoptionplatform.dto;

import com.luisdealmeida.adoptionplatform.entity.enums.Sex;
import com.luisdealmeida.adoptionplatform.entity.enums.Size;
import com.luisdealmeida.adoptionplatform.entity.enums.Species;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AnimalRequest {

    @NotBlank
    private String name;

    @NotNull
    private Species species;

    @NotBlank
    private String breed;

    @NotNull
    private Sex sex;

    @NotNull
    private Size size;

    @Min(0)
    private int ageYears;

    private String description;

    public AnimalRequest() {
    }

    public AnimalRequest(String name, Species species, String breed, Sex sex, Size size, int ageYears, String description) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.sex = sex;
        this.size = size;
        this.ageYears = ageYears;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getAgeYears() {
        return ageYears;
    }

    public void setAgeYears(int ageYears) {
        this.ageYears = ageYears;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
