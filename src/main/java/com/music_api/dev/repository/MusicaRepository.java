package com.music_api.dev.repository;

import com.music_api.dev.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MusicaRepository extends JpaRepository<Musica, UUID> {
}