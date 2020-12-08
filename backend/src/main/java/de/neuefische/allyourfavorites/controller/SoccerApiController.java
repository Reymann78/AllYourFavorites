package de.neuefische.allyourfavorites.controller;

import de.neuefische.allyourfavorites.model.Favorite;
import de.neuefische.allyourfavorites.model.SoccerTeam;
import de.neuefische.allyourfavorites.service.SoccerTeamApiCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("api/")
public class SoccerApiController {

    private final SoccerTeamApiCrawler soccerTeamApiCrawler;

    @Autowired
    public SoccerApiController(SoccerTeamApiCrawler soccerTeamApiCrawler) {
        this.soccerTeamApiCrawler = soccerTeamApiCrawler;
    }

    @GetMapping("competitions/{competitionId}/teams")
    public List<SoccerTeam> getSoccerTeamsByCompetitionId(@PathVariable String competitionId) {
        return soccerTeamApiCrawler.getSoccerTeamsByCompetitionId(competitionId);
    }

    @GetMapping("team/{teamId}/matches")
    public Favorite getMatchesOfFavoriteByTeamId(@PathVariable String teamId) throws ParseException {
        return soccerTeamApiCrawler.getMatchesOfFavoriteByTeamId(teamId);
    }

}
