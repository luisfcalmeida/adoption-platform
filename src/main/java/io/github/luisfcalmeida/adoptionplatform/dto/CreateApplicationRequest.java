package io.github.luisfcalmeida.adoptionplatform.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateApplicationRequest(
        @NotBlank @Size(max = 100) String applicantName,
        @NotBlank @Size(max = 254) @Email String applicantEmail,
        @Size(max = 20) String applicantPhone,
        String message
        ) {
}
