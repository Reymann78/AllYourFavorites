package de.neuefische.allyourfavorites.service;

import de.neuefische.allyourfavorites.apiService.SoccerApiService;
import de.neuefische.allyourfavorites.db.SoccerTeamDb;
import de.neuefische.allyourfavorites.dto.ApiSoccerTeam;
import de.neuefische.allyourfavorites.dto.ApiSoccerTeamList;
import de.neuefische.allyourfavorites.model.SoccerTeam;
import de.neuefische.allyourfavorites.model.SoccerTeamList;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class SoccerTeamService {

    private final SoccerTeamDb soccerTeamDb;
    private final SoccerApiService soccerApiService;

    public SoccerTeamService(SoccerTeamDb soccerTeamDb, SoccerApiService soccerApiService) {
        this.soccerTeamDb = soccerTeamDb;
        this.soccerApiService = soccerApiService;
    }

    @PostConstruct
    public SoccerTeamList getSoccerTeamsByCompetitionId(int competitionId) {
        List<SoccerTeam> listOfSoccerTeams = new ArrayList<>();
        SoccerTeamList soccerTeams = new SoccerTeamList();
        ApiSoccerTeamList apiSoccerTeams = soccerApiService.getSoccerTeamsByCompetitionId(competitionId);

        for (ApiSoccerTeam apiSoccerTeam : apiSoccerTeams.getTeams()) {
            SoccerTeam soccerTeam = new SoccerTeam(
                    apiSoccerTeam.getTeamId(),
                    apiSoccerTeam.getName(),
                    apiSoccerTeam.getCrestUrl());
            listOfSoccerTeams.add(soccerTeam);
        }
        soccerTeams.setSoccerTeams(listOfSoccerTeams);

        return soccerTeamDb.save(soccerTeams);
    }
}
