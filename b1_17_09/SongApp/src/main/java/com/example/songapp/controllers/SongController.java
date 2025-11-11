package com.example.songapp.controllers;

import com.example.songapp.models.Song;
import com.example.songapp.services.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/song")
public class SongController {
    @Autowired
    private IMusicService musicService;
    @GetMapping
    public String list(Model model, @ModelAttribute("message")String message, Pageable pageable){

        model.addAttribute("songs", musicService.getAll(pageable));
        return "song/list";
    }
    @GetMapping("/{id}")
    public String getSongById(@ModelAttribute("id") Song song, Model model){
//        Optional<Song> song = Optional.ofNullable(musicService.getById(id));
        model.addAttribute("song", song);
        return "song/detail";
    }
    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("song", new Song());
        model.addAttribute("artists", musicService.getArtists());
        return "song/form";
    }
    @PostMapping("/new")
    public String doCreate(@RequestParam("title") String title, @RequestParam("artist") String artistId,
                           @RequestParam("genre") String genre,
                           Model model){
        Song song = new Song();
        song.setTitle(title);
        song.setArtist(musicService.getArtistById(Integer.parseInt(artistId)));
        song.setGenre(genre);
        musicService.create(song);
        //chưa xử lí thêm bài hát
        return "redirect:/song";
    }
}
