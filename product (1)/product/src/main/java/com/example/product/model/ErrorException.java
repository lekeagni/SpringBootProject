package com.example.product.model;

import lombok.Builder;

import java.beans.Transient;
import java.time.LocalDateTime;

@Builder
public record ErrorException(
        LocalDateTime localDateTime,
        String message,
        int HttpStatus,
        @Transient
        String error
        )
{
}
