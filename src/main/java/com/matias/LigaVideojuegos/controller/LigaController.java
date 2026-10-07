package com.matias.LigaVideojuegos.controller;

import com.matias.LigaVideojuegos.dto.liga.LigaRequest;
import com.matias.LigaVideojuegos.dto.liga.LigaResponse;
import com.matias.LigaVideojuegos.service.LigaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ligas")
public class LigaController {

    private final LigaService service;

    @GetMapping
    public ResponseEntity<Page<LigaResponse>>listaLiga(
            @PageableDefault(size = 5,page = 0)Pageable pageable
            ){

        Page<LigaResponse>listaLiga = service.listarLigas(pageable);

        return ResponseEntity.ok(listaLiga);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LigaResponse>obtenerLigaId(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerLigaId(id));
    }

    @PostMapping
    public ResponseEntity<LigaResponse>crearLiga(@Valid @RequestBody
    LigaRequest request,@RequestHeader("X-Organizador") String organizador){

        LigaResponse crearLiga = service.crearLiga(request,organizador);

        return ResponseEntity.status(HttpStatus.CREATED).body(crearLiga);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>borrarLiga(@PathVariable Long id){
        service.borrarLiga(id);
        return ResponseEntity.noContent().build();
    }






}
