package com.music_api.dev.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ArtistaCreateDTO {
    @NotBlank(message = "O nome do artista é obrigatório")
    @Size(min = 2, message = "O nome do artista deve ter no mínimo 2 caractéres")
    private String nome;

    @NotBlank(message = "O gênero é obrigatório")
    private String genero;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
}
