package com.music_api.dev.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class MusicaCreateDTO {

    @NotBlank(message = "O título da música é obrigatório")
    private String titulo;

    @NotNull(message = "A duração é obrigatória")
    @Min(value = 1, message = "A duração deve ser de pelo menos 1 segundo")
    private int duracaoEmSegundos;

    @NotNull(message = "O ID do álbum é obrigatório")
    private UUID albumId;

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
}