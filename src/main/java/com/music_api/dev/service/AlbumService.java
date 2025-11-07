package com.music_api.dev.service;

import com.music_api.dev.dto.AlbumCreateDTO;
import com.music_api.dev.dto.AlbumDTO;
import com.music_api.dev.model.Album;
import com.music_api.dev.model.Artista;
import com.music_api.dev.repository.AlbumRepository;
import com.music_api.dev.repository.ArtistaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    private AlbumDTO toDTO(Album album) {
        return new AlbumDTO(album);
    }

    public AlbumDTO criar(AlbumCreateDTO dto) {
        Artista artista = artistaRepository.findById(dto.getArtistaId())
                .orElseThrow(() -> new EntityNotFoundException("Artista não encontrado com o ID: " + dto.getArtistaId()));

        Album album = new Album();
        album.setTitulo(dto.getTitulo());
        album.setAnoLancamento(dto.getAnoLancamento());
        album.setArtista(artista);

        Album albumSalvo = albumRepository.save(album);
        return toDTO(albumSalvo);
    }

    public List<AlbumDTO> listarTodos() {
        return albumRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AlbumDTO buscarPorId(UUID id) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Álbum não encontrado com o ID: " + id));
        return toDTO(album);
    }

    public AlbumDTO atualizar(UUID id, AlbumCreateDTO dto) {
        Album albumExistente = albumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Álbum não encontrado com o ID: " + id));

        Artista artista = artistaRepository.findById(dto.getArtistaId())
                .orElseThrow(() -> new EntityNotFoundException("Artista não encontrado com o ID: " + dto.getArtistaId()));

        albumExistente.setTitulo(dto.getTitulo());
        albumExistente.setAnoLancamento(dto.getAnoLancamento());
        albumExistente.setArtista(artista);

        Album albumAtualizado = albumRepository.save(albumExistente);
        return toDTO(albumAtualizado);
    }

    public void deletar(UUID id) {
        Album albumParaDeletar = albumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Álbum não encontrado com o ID: " + id));

        albumRepository.delete(albumParaDeletar);
    }
}