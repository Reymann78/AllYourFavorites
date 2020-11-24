package de.neuefische.allyourfavorites.controller;

import de.neuefische.allyourfavorites.model.SoccerTeam;
import de.neuefische.allyourfavorites.service.SoccerTeamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class SoccerApiController {

    private final SoccerTeamService soccerTeamService;

    public SoccerApiController(SoccerTeamService soccerTeamService) {
        this.soccerTeamService = soccerTeamService;
    }

    @GetMapping()
    public List<SoccerTeam> getSoccerTeamsByCompetitionId(int competitionId) {
        return soccerTeamService.getSoccerTeamsByCompetitionId(competitionId);
    }
}
