package com.music_api.dev.dto;

import com.music_api.dev.model.Album;
import java.util.UUID;

public class AlbumDTO {

    private UUID id;
    private String titulo;
    private int anoLancamento;
    private UUID artistaId;
    private String artistaNome;

    public AlbumDTO() {
    }

    public AlbumDTO(Album entidade) {
        this.id = entidade.getId();
        this.titulo = entidade.getTitulo();
        this.anoLancamento = entidade.getAnoLancamento();
        if (entidade.getArtista() != null) {
            this.artistaId = entidade.getArtista().getId();
            this.artistaNome = entidade.getArtista().getNome();
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

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public UUID getArtistaId() {
        return artistaId;
    }

    public void setArtistaId(UUID artistaId) {
        this.artistaId = artistaId;
    }

    public String getArtistaNome() {
        return artistaNome;
    }

    public void setArtistaNome(String artistaNome) {
        this.artistaNome = artistaNome;
    }
}