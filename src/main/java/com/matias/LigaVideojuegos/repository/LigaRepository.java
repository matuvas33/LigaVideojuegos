package com.matias.LigaVideojuegos.repository;

import com.matias.LigaVideojuegos.model.Liga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigaRepository extends JpaRepository<Liga,Long> {

    default Liga getByIdOrThrow(Long id){
        return findById(id).orElseThrow(()->
                new IllegalArgumentException("No se ha encontrado liga con ID "+id));
    }

}
