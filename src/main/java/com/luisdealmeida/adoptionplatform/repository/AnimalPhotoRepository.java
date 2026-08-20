package com.luisdealmeida.adoptionplatform.repository;

import com.luisdealmeida.adoptionplatform.entity.AnimalPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalPhotoRepository extends JpaRepository<AnimalPhoto, Long> {
}
