package com.matias.LigaVideojuegos.dto.equipo;

import com.matias.LigaVideojuegos.model.Liga;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EquipoRequest(
        @NotBlank
        @Size(max = 40)
        String nombre
) {
}
