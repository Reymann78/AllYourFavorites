package de.neuefische.allyourfavorites.controller;

import de.neuefische.allyourfavorites.model.Favorite;
import de.neuefische.allyourfavorites.model.SoccerTeam;
import de.neuefische.allyourfavorites.service.ApiCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class SoccerApiController {

    private final ApiCrawler apiCrawler;

    @Autowired
    public SoccerApiController(ApiCrawler apiCrawler) {
        this.apiCrawler = apiCrawler;
    }

    @GetMapping("competitions/{competitionId}/teams")
    public List<SoccerTeam> getSoccerTeamsByCompetitionId(@PathVariable String competitionId) {
        return apiCrawler.getSoccerTeamsByCompetitionId(competitionId);
    }

    @GetMapping("team/{teamId}/matches")
    public Favorite getMatchesOfFavoriteByTeamId(@PathVariable String teamId) {
        return apiCrawler.getMatchesOfFavoriteByTeamId(teamId);
    }

}
