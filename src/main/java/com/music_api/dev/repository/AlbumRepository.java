package com.music_api.dev.repository;

import com.music_api.dev.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AlbumRepository extends JpaRepository<Album, UUID> {
}