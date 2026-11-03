package io.github.luisfcalmeida.adoptionplatform.dto;

import java.util.Map;

public record ErrorResponse(
        int status,
        String message,
        Map<String, String> errors
) {
}
