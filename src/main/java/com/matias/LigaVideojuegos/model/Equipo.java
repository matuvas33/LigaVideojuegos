package com.matias.LigaVideojuegos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "equipo", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"liga_id","nombre"})
})
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre",nullable = false)
    private String nombre;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "liga_id",nullable = false)
    private Liga liga;

    public Equipo(String nombre, Liga liga) {
        this.nombre = nombre;
        this.liga = liga;
    }
}
