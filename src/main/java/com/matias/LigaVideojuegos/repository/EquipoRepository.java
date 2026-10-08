package com.matias.LigaVideojuegos.repository;

import com.matias.LigaVideojuegos.exception.excepciones.EquipoNoEncontrado;
import com.matias.LigaVideojuegos.model.Equipo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long>{

    default Equipo getIdOrThrow(Long id){
        return findById(id).orElseThrow(()->new EquipoNoEncontrado("No se ha encontrado equipo con ID "+id));
    }

    Page<Equipo> findByLigaId(Long ligaId, Pageable pageable);

}
