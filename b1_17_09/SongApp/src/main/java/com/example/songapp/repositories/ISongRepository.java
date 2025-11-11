package com.example.songapp.repositories;

import com.example.songapp.models.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ISongRepository extends
        PagingAndSortingRepository<Song, Long>,
        CrudRepository<Song, Long>{
//    List<Song> getAll();
//    Song getById(int id);
//    Song create(Song song);
//    Song update(int id, Song song);
//    void delete(int id);

//    List<Song> findAllByGenre(String genre);

    Optional<Song> findById(Long aLong);
}
