package com.music_api.dev.service;

import com.music_api.dev.dto.ArtistaCreateDTO;
import com.music_api.dev.dto.ArtistaDTO;
import com.music_api.dev.model.Artista;
import com.music_api.dev.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;


    private ArtistaDTO toDTO(Artista artista) {
        return new ArtistaDTO(artista);
    }

    private Artista toEntity(ArtistaCreateDTO dto) {
        Artista artista = new Artista();
        artista.setNome(dto.getNome());
        artista.setGenero(dto.getGenero());
        return artista;
    }

    public ArtistaDTO criar(ArtistaCreateDTO dto) {
        Artista artista = toEntity(dto);
        Artista artistaSalvo = artistaRepository.save(artista);
        return toDTO(artistaSalvo);
    }

    public Page<ArtistaDTO> listarPaginado(String generoFiltro, Pageable pageable) {
        Page<Artista> artistas;

        if (generoFiltro != null && !generoFiltro.isBlank()) {
            artistas = artistaRepository.findByGeneroContainingIgnoreCase(generoFiltro, pageable);
        } else {
            artistas = artistaRepository.findAll(pageable);
        }

        return artistas.map(this::toDTO);
    }

    public ArtistaDTO buscarPorId(UUID id) {
       Artista artista = artistaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Artista não encontrado com o ID: " + id));

        return toDTO(artista);
    }

    public ArtistaDTO atualizar(UUID id, ArtistaCreateDTO dto) {
        Artista artistaExistente = artistaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Artista não encontrado com o ID: " + id));

        artistaExistente.setNome(dto.getNome());
        artistaExistente.setGenero(dto.getGenero());

        Artista artistaAtualizado = artistaRepository.save(artistaExistente);

        return toDTO(artistaAtualizado);
    }

    public void deletar(UUID id) {
        Artista artistaParaDeletar = artistaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Artista não encontrado com o ID: " + id));

        artistaRepository.delete(artistaParaDeletar);
    }
}
