package com.matias.LigaVideojuegos.repository;

import com.matias.LigaVideojuegos.exception.excepciones.LigaNoEncontrada;
import com.matias.LigaVideojuegos.model.Liga;
import com.matias.LigaVideojuegos.model.enums.EstadoLiga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigaRepository extends JpaRepository<Liga,Long> {

    default Liga getByIdOrThrow(Long id){
        return findById(id).orElseThrow(()->
                new LigaNoEncontrada("No se ha encontrado liga con ID "+id));
    }

    Page<Liga> findByEstadoLiga(EstadoLiga estadoLiga, Pageable pageable);

    boolean existsByNombre(String nombre);
}
