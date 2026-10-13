package io.github.luisfcalmeida.adoptionplatform.repository;

import io.github.luisfcalmeida.adoptionplatform.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
