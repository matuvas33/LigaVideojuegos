package com.matias.LigaVideojuegos.service;

import com.matias.LigaVideojuegos.dto.equipo.EquipoRequest;
import com.matias.LigaVideojuegos.dto.equipo.EquipoResponse;
import com.matias.LigaVideojuegos.model.Equipo;
import com.matias.LigaVideojuegos.model.Liga;
import com.matias.LigaVideojuegos.model.enums.EstadoLiga;
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

    public Page<EquipoResponse>listarEquipos(Long ligaId,Pageable pageable){
        Liga liga = ligaRepository.getByIdOrThrow(ligaId);

        Page<Equipo>listaEquipos=equipoRepository.findByLigaId(liga.getId(),pageable);

        return listaEquipos.map(this::mapearDTO);
    }

    public void borrarEquipo(Long id){
        Equipo equipo = equipoRepository.getIdOrThrow(id);
        equipoRepository.delete(equipo);
    }


    private EquipoResponse mapearDTO(Equipo e){
      return new EquipoResponse(
              e.getId(),
              e.getNombre(),
              e.getLiga().getId(),
              e.getLiga().getNombre()
      );
    }
}
