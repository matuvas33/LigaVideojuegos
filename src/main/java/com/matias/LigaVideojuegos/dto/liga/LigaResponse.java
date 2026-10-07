package com.matias.LigaVideojuegos.dto.liga;

import com.matias.LigaVideojuegos.model.enums.EstadoLiga;

import java.time.LocalDateTime;

public record LigaResponse(
    Long id,
    String nombre,
    String organizador,
    EstadoLiga estadoLiga,
    LocalDateTime fecha_creacion
) {
}
