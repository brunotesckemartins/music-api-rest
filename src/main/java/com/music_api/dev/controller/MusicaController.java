package com.music_api.dev.controller;

import com.music_api.dev.dto.MusicaCreateDTO;
import com.music_api.dev.dto.MusicaDTO;
import com.music_api.dev.service.MusicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    @PostMapping
    public ResponseEntity<MusicaDTO> criar(@Valid @RequestBody MusicaCreateDTO dto) {
        MusicaDTO musicaCriada = musicaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(musicaCriada);
    }

    @GetMapping
    public ResponseEntity<List<MusicaDTO>> listarTodos() {
        List<MusicaDTO> musicas = musicaService.listarTodos();
        return ResponseEntity.ok(musicas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicaDTO> buscarPorId(@PathVariable UUID id) {
        MusicaDTO musica = musicaService.buscarPorId(id);
        return ResponseEntity.ok(musica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MusicaDTO> atualizar(@PathVariable UUID id, @Valid @RequestBody MusicaCreateDTO dto) {
        MusicaDTO musicaAtualizada = musicaService.atualizar(id, dto);
        return ResponseEntity.ok(musicaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        musicaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}