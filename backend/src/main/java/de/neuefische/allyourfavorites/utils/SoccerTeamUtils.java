package de.neuefische.allyourfavorites.utils;

import de.neuefische.allyourfavorites.dto.ApiSoccerTeam;
import de.neuefische.allyourfavorites.dto.ApiSoccerTeamList;
import de.neuefische.allyourfavorites.model.SoccerTeam;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SoccerTeamUtils {

    public List<SoccerTeam> getSoccerTeamsByCompetitionId(ApiSoccerTeamList apiSoccerTeams) {
        List<SoccerTeam> listOfSoccerTeams = new ArrayList<>();
        String competitionName = apiSoccerTeams.getCompetition().getName();

        for (ApiSoccerTeam apiSoccerTeam : apiSoccerTeams.getTeams()) {
            SoccerTeam soccerTeam = new SoccerTeam(
                    apiSoccerTeam.getId(),
                    apiSoccerTeam.getName(),
                    apiSoccerTeam.getCrestUrl(),
                    competitionName);
            listOfSoccerTeams.add(soccerTeam);
        }

        return listOfSoccerTeams;
    }

}
