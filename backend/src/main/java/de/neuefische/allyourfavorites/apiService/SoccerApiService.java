package de.neuefische.allyourfavorites.apiService;

import de.neuefische.allyourfavorites.dto.ApiSoccerMatchList;
import de.neuefische.allyourfavorites.dto.ApiSoccerTeamList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class SoccerApiService {

    @Value("${x.auth.token}")
    private String xAuthToken;

    private final String soccerApiUrl = "https://api.football-data.org/v2/";
    private final RestTemplate restTemplate = new RestTemplate();

    public ApiSoccerTeamList getSoccerTeamsByCompetitionId(String competitionId) {
        HttpEntity<String> entity = new HttpEntity<>("parameters", createHttpHeaders(xAuthToken));
        ResponseEntity<ApiSoccerTeamList> response = restTemplate
                .exchange(soccerApiUrl + "competitions/" + competitionId + "/teams", HttpMethod.GET, entity, ApiSoccerTeamList.class);
        return Objects.requireNonNull(response.getBody());
    }

    public ApiSoccerMatchList getMatchesOfFavoriteByTeamId(String teamId) {
        HttpEntity<String> entity = new HttpEntity<>("parameter", createHttpHeaders(xAuthToken));
        ResponseEntity<ApiSoccerMatchList> response = restTemplate
                .exchange(soccerApiUrl + "teams/" + teamId + "/matches", HttpMethod.GET, entity, ApiSoccerMatchList.class);
        return Objects.requireNonNull(response.getBody());
    }

    private HttpHeaders createHttpHeaders(String xAuthToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-auth-token", xAuthToken);
        return headers;
    }

}
