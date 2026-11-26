package io.github.luisfcalmeida.adoptionplatform.repository;

import io.github.luisfcalmeida.adoptionplatform.entity.AdoptionApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionApplicationRepository extends JpaRepository<AdoptionApplication, Long> {
}
