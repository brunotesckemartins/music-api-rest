package com.music_api.dev.service;

import com.music_api.dev.dto.MusicaCreateDTO;
import com.music_api.dev.dto.MusicaDTO;
import com.music_api.dev.model.Album;
import com.music_api.dev.model.Musica;
import com.music_api.dev.repository.AlbumRepository;
import com.music_api.dev.repository.MusicaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MusicaService {

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private AlbumRepository albumRepository;

    private MusicaDTO toDTO(Musica musica) {
        return new MusicaDTO(musica);
    }

    public MusicaDTO criar(MusicaCreateDTO dto) {
        Album album = albumRepository.findById(dto.getAlbumId())
                .orElseThrow(() -> new EntityNotFoundException("Álbum não encontrado com o ID: " + dto.getAlbumId()));

        Musica musica = new Musica();
        musica.setTitulo(dto.getTitulo());
        musica.setDuracaoEmSegundos(dto.getDuracaoEmSegundos());
        musica.setAlbum(album);

        Musica musicaSalva = musicaRepository.save(musica);
        return toDTO(musicaSalva);
    }

    public List<MusicaDTO> listarTodos() {
        return musicaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public MusicaDTO buscarPorId(UUID id) {
        Musica musica = musicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Música não encontrada com o ID: " + id));
        return toDTO(musica);
    }

    public MusicaDTO atualizar(UUID id, MusicaCreateDTO dto) {
        Musica musicaExistente = musicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Música não encontrada com o ID: " + id));

        Album album = albumRepository.findById(dto.getAlbumId())
                .orElseThrow(() -> new EntityNotFoundException("Álbum não encontrado com o ID: " + dto.getAlbumId()));

        musicaExistente.setTitulo(dto.getTitulo());
        musicaExistente.setDuracaoEmSegundos(dto.getDuracaoEmSegundos());
        musicaExistente.setAlbum(album);

        Musica musicaAtualizada = musicaRepository.save(musicaExistente);
        return toDTO(musicaAtualizada);
    }

    public void deletar(UUID id) {
        Musica musicaParaDeletar = musicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Música não encontrada com o ID: " + id));

        musicaRepository.delete(musicaParaDeletar);
    }
}