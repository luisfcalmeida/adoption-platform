package io.github.luisfcalmeida.adoptionplatform.dto;

import io.github.luisfcalmeida.adoptionplatform.entity.Animal;
import io.github.luisfcalmeida.adoptionplatform.enums.AdoptionStatus;
import io.github.luisfcalmeida.adoptionplatform.enums.AnimalSex;
import io.github.luisfcalmeida.adoptionplatform.enums.AnimalSize;
import io.github.luisfcalmeida.adoptionplatform.enums.AnimalSpecies;

import java.time.LocalDate;

public record AnimalResponse(
        Long id,
        String name,
        String description,
        String photoUrl,
        AnimalSpecies species,
        AnimalSize size,
        AnimalSex sex,
        LocalDate birthDate,
        AdoptionStatus adoptionStatus,
        AssociationSummary association
) {
    public static AnimalResponse from(Animal animal) {
        return new AnimalResponse(
                animal.getId(),
                animal.getName(),
                animal.getDescription(),
                animal.getPhotoUrl(),
                animal.getSpecies(),
                animal.getSize(),
                animal.getSex(),
                animal.getBirthDate(),
                animal.getAdoptionStatus(),
                AssociationSummary.from(animal.getAssociation()));
    }
}