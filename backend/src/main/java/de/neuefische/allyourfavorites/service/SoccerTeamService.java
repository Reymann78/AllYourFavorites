package de.neuefische.allyourfavorites.service;

import de.neuefische.allyourfavorites.api.SoccerApiService;
import de.neuefische.allyourfavorites.model.SoccerTeam;
import de.neuefische.allyourfavorites.model.SoccerTeamListDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoccerTeamService {

    private final SoccerApiService soccerApiService;

    public SoccerTeamService(SoccerApiService soccerApiService) {
        this.soccerApiService = soccerApiService;
    }

    public List<SoccerTeam> getSoccerTeamsByCompetitionId(int competitionId) {
        List<SoccerTeamListDto>
    }
}
