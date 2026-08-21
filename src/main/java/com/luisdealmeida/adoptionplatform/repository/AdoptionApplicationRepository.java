package com.luisdealmeida.adoptionplatform.repository;

import com.luisdealmeida.adoptionplatform.entity.AdoptionApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdoptionApplicationRepository extends JpaRepository<AdoptionApplication, Long>, JpaSpecificationExecutor<AdoptionApplication> {
}
