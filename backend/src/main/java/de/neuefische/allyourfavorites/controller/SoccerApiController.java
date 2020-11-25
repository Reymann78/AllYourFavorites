package de.neuefische.allyourfavorites.controller;

import de.neuefische.allyourfavorites.model.SoccerTeamList;
import de.neuefische.allyourfavorites.service.SoccerTeamService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class SoccerApiController {

    private final SoccerTeamService soccerTeamService;

    public SoccerApiController(SoccerTeamService soccerTeamService) {
        this.soccerTeamService = soccerTeamService;
    }

    @GetMapping("competitions/{competitionId}/teams")
    public SoccerTeamList getSoccerTeamsByCompetitionId(@PathVariable String competitionId) {
        return soccerTeamService.getSoccerTeamsByCompetitionId(competitionId);
    }
}
