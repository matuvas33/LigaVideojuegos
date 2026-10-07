package com.matias.LigaVideojuegos.dto.liga;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LigaRequest(
    @NotBlank
    @Size(max = 60)
    String nombre
){
}
