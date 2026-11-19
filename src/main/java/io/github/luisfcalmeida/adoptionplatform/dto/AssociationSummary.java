package io.github.luisfcalmeida.adoptionplatform.dto;

import io.github.luisfcalmeida.adoptionplatform.entity.Association;

public record AssociationSummary(
        Long id,
        String name
) {
    public static AssociationSummary from(Association association) {
        return new AssociationSummary(association.getId(), association.getName());
    }
}
