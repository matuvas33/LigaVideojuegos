package com.matias.LigaVideojuegos.repository;

import com.matias.LigaVideojuegos.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long>{

}
