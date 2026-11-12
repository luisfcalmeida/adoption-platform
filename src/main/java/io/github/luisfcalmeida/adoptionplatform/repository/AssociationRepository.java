package io.github.luisfcalmeida.adoptionplatform.repository;

import io.github.luisfcalmeida.adoptionplatform.entity.Association;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociationRepository extends JpaRepository<Association, Long> {
}
