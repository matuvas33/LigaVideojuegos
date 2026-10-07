package com.matias.LigaVideojuegos.controller;

import com.matias.LigaVideojuegos.dto.equipo.EquipoRequest;
import com.matias.LigaVideojuegos.dto.equipo.EquipoResponse;
import com.matias.LigaVideojuegos.service.EquipoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ligas/{ligaId}/equipos")
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoService service;

    @PostMapping
    public ResponseEntity<EquipoResponse>crearEquipo(@PathVariable Long ligaId,
                                                     @Valid @RequestBody EquipoRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearEquipo(request,ligaId));

    }

    @GetMapping
    public ResponseEntity<Page<EquipoResponse>>listaEquipos(
            @PageableDefault(size = 5,page = 0) Pageable pageable
            ){
        Page<EquipoResponse>listaEquipos = service.listarEquipos(pageable);

        return ResponseEntity.ok(listaEquipos);
    }



}
