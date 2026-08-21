package com.luisdealmeida.adoptionplatform.repository;

import com.luisdealmeida.adoptionplatform.entity.AdoptionApplication;
import com.luisdealmeida.adoptionplatform.entity.enums.ApplicationStatus;
import org.springframework.data.jpa.domain.Specification;

public final class AdoptionApplicationSpecifications {

    private AdoptionApplicationSpecifications() {
    }

    public static Specification<AdoptionApplication> hasStatus(ApplicationStatus status) {
        return (root, query, cb) -> status == null ? null : cb.equal(root.get("status"), status);
    }

    public static Specification<AdoptionApplication> hasAnimalId(Long animalId) {
        return (root, query, cb) -> animalId == null ? null : cb.equal(root.get("animal").get("id"), animalId);
    }
}
