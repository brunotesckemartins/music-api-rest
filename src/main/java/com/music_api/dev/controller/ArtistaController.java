package com.music_api.dev.controller;

import com.music_api.dev.dto.ArtistaCreateDTO;
import com.music_api.dev.dto.ArtistaDTO;
import com.music_api.dev.service.ArtistaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @PostMapping
    public ResponseEntity<ArtistaDTO> criar(@Valid @RequestBody ArtistaCreateDTO dto) {
        ArtistaDTO artistaCriado = artistaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(artistaCriado);
    }

    @GetMapping
    public ResponseEntity<Page<ArtistaDTO>> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,

            @RequestParam(defaultValue = "nome") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,

            @RequestParam(required = false) String generoFiltro
    ) {

        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ArtistaDTO> artistas = artistaService.listarPaginado(generoFiltro, pageable);

        return ResponseEntity.ok(artistas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistaDTO> buscarPorId(@PathVariable UUID id) {
        ArtistaDTO artista = artistaService.buscarPorId(id);
        return ResponseEntity.ok(artista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistaDTO> atualizar(@PathVariable UUID id, @Valid @RequestBody ArtistaCreateDTO dto) {
        ArtistaDTO artistaAtualizado = artistaService.atualizar(id, dto);
        return ResponseEntity.ok(artistaAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        artistaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}