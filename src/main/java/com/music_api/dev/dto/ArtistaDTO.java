package com.music_api.dev.dto;

import com.music_api.dev.model.Artista;
import java.util.UUID;

public class ArtistaDTO {
    private UUID id;
    private String nome;
    private String genero;

    public ArtistaDTO() {
    }

    public ArtistaDTO(Artista entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.genero = entidade.getGenero();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
}
