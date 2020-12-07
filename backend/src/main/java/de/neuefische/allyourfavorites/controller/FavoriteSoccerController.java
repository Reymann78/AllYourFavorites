package de.neuefische.allyourfavorites.controller;

import de.neuefische.allyourfavorites.dto.TeamIdDto;
import de.neuefische.allyourfavorites.model.FavoriteMatches;
import de.neuefische.allyourfavorites.model.SoccerTeam;
import de.neuefische.allyourfavorites.model.User;
import de.neuefische.allyourfavorites.service.FavoriteSoccerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<String> favoritesOfUser = new ArrayList<>(favoriteSoccerService.getAllFavoritesOfUser(principal.getName()));
        return favoriteSoccerService.getAllMatchesOfFavorites(favoritesOfUser);
    }

    @PostMapping
    public Optional<User> addFavorite(@RequestBody TeamIdDto favoriteTeam, Principal principal) {
        return favoriteSoccerService.addFavoriteTeamId(favoriteTeam.getTeamId(), principal.getName());
    }

    @DeleteMapping("{teamId}")
    public void removeFavorite(@PathVariable String teamId, Principal principal) {
        favoriteSoccerService.removeFavoriteTeamId(teamId, principal.getName());
    }

}
