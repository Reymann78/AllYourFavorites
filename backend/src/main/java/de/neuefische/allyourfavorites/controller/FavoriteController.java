package de.neuefische.allyourfavorites.controller;

import de.neuefische.allyourfavorites.dto.StandingParamsDto;
import de.neuefische.allyourfavorites.dto.TeamIdDto;
import de.neuefische.allyourfavorites.model.Favorite;
import de.neuefische.allyourfavorites.model.SoccerLeagueTable;
import de.neuefische.allyourfavorites.model.User;
import de.neuefische.allyourfavorites.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping
    public Iterable<Favorite> getAllMatchesOfFavoritesOfUser(Principal principal) {
        return favoriteService.getAllMatchesOfFavorites(favoriteService.getAllFavoritesOfUser(principal.getName()));
    }

    @GetMapping("/standings")
    public SoccerLeagueTable getSoccerLeagueTable(@RequestBody StandingParamsDto allParams) {
        String competitionId = allParams.getCompetitionId();
        String matchDay = allParams.getMatchDay();
        String groupName = allParams.getGroupName();
        String tableType = allParams.getTableType();
        return favoriteService.getSoccerLeagueTable(competitionId, matchDay, groupName, tableType);
    }

    @PostMapping
    public Optional<User> addFavorite(@RequestBody TeamIdDto favoriteTeam, Principal principal) {
        return favoriteService.addFavoriteTeamId(favoriteTeam.getTeamId(), principal.getName());
    }

    @DeleteMapping("{teamId}")
    public void removeFavorite(@PathVariable String teamId, Principal principal) {
        favoriteService.removeFavoriteTeamId(teamId, principal.getName());
    }

}
