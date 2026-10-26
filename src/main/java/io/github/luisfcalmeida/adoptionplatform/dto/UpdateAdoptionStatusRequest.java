package io.github.luisfcalmeida.adoptionplatform.dto;

import io.github.luisfcalmeida.adoptionplatform.enums.AdoptionStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateAdoptionStatusRequest(
        @NotNull AdoptionStatus adoptionStatus) {
}
