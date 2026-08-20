package com.luisdealmeida.adoptionplatform.repository;

import com.luisdealmeida.adoptionplatform.entity.Animal;
import com.luisdealmeida.adoptionplatform.entity.enums.AdoptionStatus;
import com.luisdealmeida.adoptionplatform.entity.enums.Sex;
import com.luisdealmeida.adoptionplatform.entity.enums.Size;
import com.luisdealmeida.adoptionplatform.entity.enums.Species;
import org.springframework.data.jpa.domain.Specification;

public final class AnimalSpecifications {

    private AnimalSpecifications() {
    }

    public static Specification<Animal> hasAdoptionStatus(AdoptionStatus status) {
        return (root, query, cb) -> cb.equal(root.get("adoptionStatus"), status);
    }

    public static Specification<Animal> hasSpecies(Species species) {
        return (root, query, cb) -> species == null ? null : cb.equal(root.get("species"), species);
    }

    public static Specification<Animal> hasSex(Sex sex) {
        return (root, query, cb) -> sex == null ? null : cb.equal(root.get("sex"), sex);
    }

    public static Specification<Animal> hasSize(Size size) {
        return (root, query, cb) -> size == null ? null : cb.equal(root.get("size"), size);
    }

    public static Specification<Animal> ageBetween(Integer minAge, Integer maxAge) {
        return (root, query, cb) -> {
            if (minAge == null && maxAge == null) {
                return null;
            }
            if (minAge != null && maxAge != null) {
                return cb.between(root.get("ageYears"), minAge, maxAge);
            }
            if (minAge != null) {
                return cb.greaterThanOrEqualTo(root.get("ageYears"), minAge);
            }
            return cb.lessThanOrEqualTo(root.get("ageYears"), maxAge);
        };
    }
}