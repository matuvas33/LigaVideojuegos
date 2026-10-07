package com.matias.LigaVideojuegos.service;

import com.matias.LigaVideojuegos.dto.liga.LigaRequest;
import com.matias.LigaVideojuegos.dto.liga.LigaResponse;
import com.matias.LigaVideojuegos.model.Liga;
import com.matias.LigaVideojuegos.repository.LigaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LigaService {

    private final LigaRepository ligaRepository;

    public Page<LigaResponse> listarLigas(Pageable pageable){

        Page<Liga> paginaLiga = ligaRepository.findAll(pageable);
        return paginaLiga.map(this::mapearDto);
    }

    public LigaResponse obtenerLigaId(Long id){
        Liga ligaEncontrada = ligaRepository.getByIdOrThrow(id);
        return mapearDto(ligaEncontrada);
    }

    public LigaResponse crearLiga(LigaRequest request,String organizador){
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
