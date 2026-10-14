package io.github.luisfcalmeida.adoptionplatform.dto;

import io.github.luisfcalmeida.adoptionplatform.enums.AnimalSex;
import io.github.luisfcalmeida.adoptionplatform.enums.AnimalSize;
import io.github.luisfcalmeida.adoptionplatform.enums.AnimalSpecies;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateAnimalRequest(
        @NotBlank @Size(max = 50) String name,
        String description,
        String photoUrl,
        @NotNull AnimalSpecies species,
        AnimalSize size,
        @NotNull AnimalSex sex,
        @PastOrPresent LocalDate birthDate) {
}