package com.matias.LigaVideojuegos.dto.error;

import java.time.LocalDateTime;

public record MensajeDetalles(
        String estado,
        String error,
        String mensaje,
        String detalles,
        LocalDateTime fecha
) {
}
