package com.luisdealmeida.adoptionplatform.dto;

import java.time.LocalDateTime;

public class StaffAccountDto {

    private final Long id;
    private final String email;
    private final boolean active;
    private final LocalDateTime createdAt;

    public StaffAccountDto(Long id, String email, boolean active, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.active = active;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
