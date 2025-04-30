package com.example.client.model;

import jakarta.persistence.Transient;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorException(
        LocalDateTime localDateTime,
        String message,
        int httpStatus,
        @Transient
        String error
) {

}
