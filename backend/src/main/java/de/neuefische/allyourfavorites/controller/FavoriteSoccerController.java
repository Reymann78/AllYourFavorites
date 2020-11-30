package de.neuefische.allyourfavorites.controller;

import de.neuefische.allyourfavorites.model.SoccerTeam;
import de.neuefische.allyourfavorites.service.FavoriteSoccerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteSoccerController {

    private final FavoriteSoccerService favoriteSoccerService;

    @Autowired
    public FavoriteSoccerController(FavoriteSoccerService favoriteSoccerService) {
        this.favoriteSoccerService = favoriteSoccerService;
    }

    @GetMapping("soccerTeams")
    public List<SoccerTeam> getAllSoccerTeamsOfSelectedLeague() {
        return favoriteSoccerService.getListOfSoccerTeams();
    }

    @PostMapping
    public String addToFavorites(@RequestBody String nameOfFavorite, Principal principal) {
        return this.favoriteSoccerService.add(nameOfFavorite, principal.getName());
    }

}
