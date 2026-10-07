package com.matias.LigaVideojuegos.model;

import com.matias.LigaVideojuegos.model.enums.EstadoLiga;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "liga")
public class Liga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre",nullable = false,length = 60)
    private String nombre;
    @Column(name = "organizador",nullable = false,length = 50)
    private String organizador;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado",nullable = false)
    private EstadoLiga estadoLiga=EstadoLiga.INSCRIPCION;
    @Column(name = "fecha_creacion",updatable = false,nullable = false)
    private LocalDateTime fecha_creacion=LocalDateTime.now();

    public Liga(String nombre, String organizador) {
        this.nombre = nombre;
        this.organizador = organizador;
    }
}
