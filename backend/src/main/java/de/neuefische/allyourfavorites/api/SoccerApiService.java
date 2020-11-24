package de.neuefische.allyourfavorites.api;

import de.neuefische.allyourfavorites.model.SoccerTeam;
import de.neuefische.allyourfavorites.model.SoccerTeamListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SoccerApiService {

    @Value("")
    private String xAuthToken;
    private String soccerApiUrl = "https://api.football-data.org/v2/";

    private final RestTemplate restTemplate = new RestTemplate();

    public List<SoccerTeam> getSoccerTeamsByCompetitionId(int competitionId) {
        ResponseEntity<SoccerTeamListDto> response = restTemplate.getForEntity(soccerApiUrl + "competitions" + competitionId, SoccerTeamListDto.class);
        return response.getBody().getSoccerTeams();
    }


}
