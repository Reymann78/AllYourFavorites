package de.neuefische.allyourfavorites.controller;

import de.neuefische.allyourfavorites.model.SoccerTeam;
import de.neuefische.allyourfavorites.service.SoccerTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/soccerTeams")
public class SoccerTeamController {

    private final SoccerTeamService soccerTeamService;

    @Autowired
    public SoccerTeamController(SoccerTeamService soccerTeamService) {
        this.soccerTeamService = soccerTeamService;
    }

    @GetMapping
    public List<SoccerTeam> getAllSoccerTeamsOfSelectedLeague() {
        return soccerTeamService.getListOfSoccerTeams();
    }
}
