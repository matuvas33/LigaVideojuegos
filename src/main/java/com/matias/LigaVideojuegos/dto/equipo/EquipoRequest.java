package com.matias.LigaVideojuegos.dto.equipo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EquipoRequest(
        @NotBlank
        @Size(min = 2,max = 40)
        String nombre
) {
}
