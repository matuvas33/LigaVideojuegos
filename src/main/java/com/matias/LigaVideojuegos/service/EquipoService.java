package com.matias.LigaVideojuegos.service;

import com.matias.LigaVideojuegos.dto.equipo.EquipoRequest;
import com.matias.LigaVideojuegos.dto.equipo.EquipoResponse;
import com.matias.LigaVideojuegos.model.Equipo;
import com.matias.LigaVideojuegos.model.Liga;
import com.matias.LigaVideojuegos.repository.EquipoRepository;
import com.matias.LigaVideojuegos.repository.LigaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipoService {
    private final EquipoRepository equipoRepository;
    private final LigaRepository ligaRepository;

    public EquipoResponse crearEquipo(EquipoRequest request,Long id){

        Liga liga = ligaRepository.getByIdOrThrow(id);

        Equipo nuevoEquipo = new Equipo(request.nombre(),liga);

        Equipo guardado = equipoRepository.save(nuevoEquipo);

        return mapearDTO(guardado);
    }

    public Page<EquipoResponse>listarEquipos(Pageable pageable){
        Page<Equipo>listaEquipos=equipoRepository.findAll(pageable);

        return listaEquipos.map(this::mapearDTO);
    }


    private EquipoResponse mapearDTO(Equipo e){
        Liga liga = ligaRepository.getByIdOrThrow(e.getId());
      return new EquipoResponse(
              e.getId(),
              e.getNombre(),
              liga.getId(),
              liga.getNombre()
      );
    }
}
