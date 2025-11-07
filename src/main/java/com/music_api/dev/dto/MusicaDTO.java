package com.music_api.dev.dto;

import com.music_api.dev.model.Musica;
import java.util.UUID;

public class MusicaDTO {

    private UUID id;
    private String titulo;
    private int duracaoEmSegundos;
    private UUID albumId;
    private String albumTitulo;

    public MusicaDTO() {
    }

    public MusicaDTO(Musica entidade) {
        this.id = entidade.getId();
        this.titulo = entidade.getTitulo();
        this.duracaoEmSegundos = entidade.getDuracaoEmSegundos();
        if (entidade.getAlbum() != null) {
            this.albumId = entidade.getAlbum().getId();
            this.albumTitulo = entidade.getAlbum().getTitulo();
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracaoEmSegundos() {
        return duracaoEmSegundos;
    }

    public void setDuracaoEmSegundos(int duracaoEmSegundos) {
        this.duracaoEmSegundos = duracaoEmSegundos;
    }

    public UUID getAlbumId() {
        return albumId;
    }

    public void setAlbumId(UUID albumId) {
        this.albumId = albumId;
    }

    public String getAlbumTitulo() {
        return albumTitulo;
    }

    public void setAlbumTitulo(String albumTitulo) {
        this.albumTitulo = albumTitulo;
    }
}