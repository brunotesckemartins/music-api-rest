package com.music_api.dev.controller;

import com.music_api.dev.dto.AlbumCreateDTO;
import com.music_api.dev.dto.AlbumDTO;
import com.music_api.dev.service.AlbumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/albuns")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping
    public ResponseEntity<AlbumDTO> criar(@Valid @RequestBody AlbumCreateDTO dto) {
        AlbumDTO albumCriado = albumService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(albumCriado);
    }

    @GetMapping
    public ResponseEntity<List<AlbumDTO>> listarTodos() {
        List<AlbumDTO> albuns = albumService.listarTodos();
        return ResponseEntity.ok(albuns);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> buscarPorId(@PathVariable UUID id) {
        AlbumDTO album = albumService.buscarPorId(id);
        return ResponseEntity.ok(album);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumDTO> atualizar(@PathVariable UUID id, @Valid @RequestBody AlbumCreateDTO dto) {
        AlbumDTO albumAtualizado = albumService.atualizar(id, dto);
        return ResponseEntity.ok(albumAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        albumService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}