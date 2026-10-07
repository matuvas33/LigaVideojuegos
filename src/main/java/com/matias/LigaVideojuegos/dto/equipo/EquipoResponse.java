package com.matias.LigaVideojuegos.dto.equipo;

public record EquipoResponse (
    Long id,
    String nombre,
    Long idLiga,
    String nombreLiga
){
}
