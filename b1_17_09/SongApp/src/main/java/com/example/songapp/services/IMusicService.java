package com.example.songapp.services;

import com.example.songapp.models.Artist;
import com.example.songapp.models.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMusicService {
    Page<Song> getAll(Pageable pageable);
    Song getById(int id);
    Song create(Song song);
    Song update(int id, Song song);
    void delete(int id);

    Artist getArtistById(int id);
    Artist createArtist(Artist artist);
    List<Song> getSongsByArtistId(int id);
    List<Artist> getArtists();
//    Artist updateArtist(Artist artist);
//    Artist deleteArtist(int id);
}
