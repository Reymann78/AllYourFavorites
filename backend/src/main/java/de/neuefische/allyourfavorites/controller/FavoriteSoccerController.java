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

    @GetMapping("favorites")
    public List<String> getAllFavoritesOfUser(Principal principal) {
        return favoriteSoccerService.getAllFavoritesOfUser(principal.getName());
    }

    @PostMapping
    public String addFavorite(@RequestBody String favoriteTeamName, Principal principal) {
        return this.favoriteSoccerService.addFavorite(favoriteTeamName, principal.getName());
    }

    @DeleteMapping("delete")
    public void removeFavorite(@RequestBody String favoriteTeamName, Principal principal) {
        favoriteSoccerService.removeFavorite(favoriteTeamName, principal.getName());
    }

}
