package de.neuefische.allyourfavorites.service;

import de.neuefische.allyourfavorites.apiService.SoccerApiService;
import de.neuefische.allyourfavorites.db.SoccerTeamDb;
import de.neuefische.allyourfavorites.dto.ApiSoccerTeam;
import de.neuefische.allyourfavorites.dto.ApiSoccerTeamList;
import de.neuefische.allyourfavorites.model.SoccerTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class SoccerTeamService {

    private final SoccerTeamDb soccerTeamDb;
    private final SoccerApiService soccerApiService;
    private final static String BUNDESLIGA = "2002";

    @Autowired
    public SoccerTeamService(SoccerTeamDb soccerTeamDb, SoccerApiService soccerApiService) {
        this.soccerTeamDb = soccerTeamDb;
        this.soccerApiService = soccerApiService;
    }

    @PostConstruct
    private void postConstruct() {
        List<SoccerTeam> list = getSoccerTeamsByCompetitionId(BUNDESLIGA);
        for(SoccerTeam soccerTeam : list) {
            soccerTeamDb.save(soccerTeam);
        }
    }

    public List<SoccerTeam> getSoccerTeamsByCompetitionId(String competitionId) {
        List<SoccerTeam> listOfSoccerTeams = new ArrayList<>();
        ApiSoccerTeamList apiSoccerTeams = soccerApiService.getSoccerTeamsByCompetitionId(competitionId);
        for (ApiSoccerTeam apiSoccerTeam : apiSoccerTeams.getTeams()) {
            SoccerTeam soccerTeam = new SoccerTeam(
                    apiSoccerTeam.getId(),
                    apiSoccerTeam.getName(),
                    apiSoccerTeam.getCrestUrl());
            listOfSoccerTeams.add(soccerTeam);
        }

        return listOfSoccerTeams;
    }
}
