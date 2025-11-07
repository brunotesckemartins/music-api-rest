package com.music_api.dev.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class AlbumCreateDTO {

    @NotBlank(message = "O título do álbum é obrigatório")
    private String titulo;

    @NotNull(message = "O ano de lançamento é obrigatório")
    @Min(value = 1800, message = "O ano de lançamento deve ser válido")
    private int anoLancamento;

    @NotNull(message = "O ID do artista é obrigatório")
    private UUID artistaId;

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
}