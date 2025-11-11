package com.example.songapp.controllers;

import com.example.songapp.models.Artist;
import com.example.songapp.repositories.IArtistRepository;
import com.example.songapp.services.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/artists")
public class ArtistController {
    @Autowired
    private IMusicService musicService;

    @GetMapping
    public String getArtists(Model model) {
        List<Artist> artists = musicService.getArtists();
        model.addAttribute("artists", artists);
        return "artist/list";
    }

    @GetMapping("/new")
    public String getNewArtist(Model model) {
        model.addAttribute("artist", new Artist());
        return "artist/form";
    }

    @PostMapping("/new")
    public String saveArtist(Artist artist, Model model) {
        musicService.createArtist(artist);
        model.addAttribute("message", "Artist has been saved successfully!");
        return "redirect:/artists";
    }
}
