package com.matias.LigaVideojuegos.dto.error;

import java.time.LocalDateTime;

public record MensajeError(
        String estado,
        String error,
        String mensaje,
        LocalDateTime fecha
) {
}
