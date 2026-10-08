package com.matias.LigaVideojuegos.service;

import com.matias.LigaVideojuegos.dto.liga.LigaRequest;
import com.matias.LigaVideojuegos.dto.liga.LigaResponse;
import com.matias.LigaVideojuegos.exception.excepciones.NombreRepetido;
import com.matias.LigaVideojuegos.model.Liga;
import com.matias.LigaVideojuegos.model.enums.EstadoLiga;
import com.matias.LigaVideojuegos.repository.LigaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class LigaService {

    private final LigaRepository ligaRepository;

    public Page<LigaResponse> listarLigas(@RequestParam(required = false) EstadoLiga estado, Pageable pageable){

        if (estado!=null){
            Page<Liga>paginaLigaFiltrada=ligaRepository.findByEstadoLiga(estado,pageable);
            return paginaLigaFiltrada.map(this::mapearDto);
        }
        Page<Liga> paginaLiga = ligaRepository.findAll(pageable);
        return paginaLiga.map(this::mapearDto);
    }

    public LigaResponse obtenerLigaId(Long id){
        Liga ligaEncontrada = ligaRepository.getByIdOrThrow(id);
        return mapearDto(ligaEncontrada);
    }

    public LigaResponse crearLiga(LigaRequest request,String organizador){
      if (ligaRepository.existsByNombre(request.nombre())){
          throw new NombreRepetido("No se puede tener nombres de ligas repetidas!");
      }
        Liga nuevaLiga= new Liga(request.nombre(),organizador);

        Liga guardada = ligaRepository.save(nuevaLiga);

        return mapearDto(guardada);

    }

    public void borrarLiga(Long id){
        Liga ligaEncontrada = ligaRepository.getByIdOrThrow(id);
        ligaRepository.delete(ligaEncontrada);

    }

    private LigaResponse mapearDto(Liga liga){
        return new LigaResponse(
                liga.getId(),
                liga.getNombre(),
                liga.getOrganizador(),
                liga.getEstadoLiga(),
                liga.getFecha_creacion()
        );
    }

}
