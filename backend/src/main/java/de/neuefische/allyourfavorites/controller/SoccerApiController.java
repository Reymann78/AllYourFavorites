package de.neuefische.allyourfavorites.controller;

import de.neuefische.allyourfavorites.model.SoccerTeam;
import de.neuefische.allyourfavorites.service.SoccerTeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class SoccerApiController {

    private final SoccerTeamService soccerTeamService;

    public SoccerApiController(SoccerTeamService soccerTeamService) {
        this.soccerTeamService = soccerTeamService;
    }

    @GetMapping("competitions/{competitionId}/teams")
    public List<SoccerTeam> getSoccerTeamsByCompetitionId( @PathVariable int competitionId) {
        return soccerTeamService.getSoccerTeamsByCompetitionId(competitionId);
    }
}
