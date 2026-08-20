package com.luisdealmeida.adoptionplatform.dto;

import java.time.LocalDateTime;

public class AnimalPhotoDto {

    private final Long id;
    private final String fileName;
    private final LocalDateTime uploadedAt;

    public AnimalPhotoDto(Long id, String fileName, LocalDateTime uploadedAt) {
        this.id = id;
        this.fileName = fileName;
        this.uploadedAt = uploadedAt;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
}
