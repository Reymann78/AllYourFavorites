package de.neuefische.allyourfavorites.controller;

import de.neuefische.allyourfavorites.dto.TeamIdDto;
import de.neuefische.allyourfavorites.model.FavoriteMatches;
import de.neuefische.allyourfavorites.model.SoccerTeam;
import de.neuefische.allyourfavorites.service.FavoriteSoccerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
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

    @GetMapping
    public Iterable<FavoriteMatches> getAllMatchesOfFavoritesOfUser(Principal principal) {
        return favoriteSoccerService.getAllMatchesOfFavorites(favoriteSoccerService.getAllFavoritesOfUser(principal.getName()));
    }

    @PostMapping
    public String addFavorite(@RequestBody TeamIdDto favoriteTeam, Principal principal) {
        return this.favoriteSoccerService.addFavoriteTeamId(favoriteTeam.getTeamId(), principal.getName());
    }

    @DeleteMapping("delete")
    public void removeFavorite(@RequestBody String favoriteTeamId, Principal principal) {
        favoriteSoccerService.removeFavoriteTeamId(favoriteTeamId, principal.getName());
    }

}
