package com.luisdealmeida.adoptionplatform.exception;

import java.time.Instant;
import java.util.Map;

public class ApiErrorResponse {

    private final Instant timestamp;
    private final int status;
    private final String message;
    private final Map<String, String> fieldErrors;

    public ApiErrorResponse(Instant timestamp, int status, String message, Map<String, String> fieldErrors) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.fieldErrors = fieldErrors;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }
}
