package io.github.luisfcalmeida.adoptionplatform.entity;

import io.github.luisfcalmeida.adoptionplatform.enums.AdoptionStatus;
import io.github.luisfcalmeida.adoptionplatform.enums.AnimalSex;
import io.github.luisfcalmeida.adoptionplatform.enums.AnimalSize;
import io.github.luisfcalmeida.adoptionplatform.enums.AnimalSpecies;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "photo_url")
    private String photoUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AnimalSpecies species;

    @Enumerated(EnumType.STRING)
    private AnimalSize size;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AnimalSex sex;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "adoption_status")
    private AdoptionStatus status = AdoptionStatus.AVAILABLE;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public Animal() {
    }

    public Animal(String name, String description, String photoUrl, AnimalSpecies species, AnimalSize size, AnimalSex sex, LocalDate birthDate) {
        this.name = name;
        this.description = description;
        this.photoUrl = photoUrl;
        this.species = species;
        this.size = size;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public AnimalSpecies getSpecies() {
        return species;
    }

    public void setSpecies(AnimalSpecies species) {
        this.species = species;
    }

    public AnimalSize getSize() {
        return size;
    }

    public void setSize(AnimalSize size) {
        this.size = size;
    }

    public AnimalSex getSex() {
        return sex;
    }

    public void setSex(AnimalSex sex) {
        this.sex = sex;
    }

    public AdoptionStatus getStatus() {
        return status;
    }

    public void setStatus(AdoptionStatus status) {
        this.status = status;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

}
