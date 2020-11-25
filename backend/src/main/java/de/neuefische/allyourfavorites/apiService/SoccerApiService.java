package de.neuefische.allyourfavorites.apiService;

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

    @Value("${api.xAuthToken: defaultXAuthToken")
    private String xAuthToken;

    private final String soccerApiUrl = "https://api.football-data.org/v2/";
    private final RestTemplate restTemplate = new RestTemplate();

    public ApiSoccerTeamList getSoccerTeamsByCompetitionId(int competitionId) {
        HttpEntity<String> entity = new HttpEntity<>("parameters", createHttpHeaders(xAuthToken));
        ResponseEntity<ApiSoccerTeamList> response = restTemplate
                .exchange(soccerApiUrl + "competitions" + competitionId, HttpMethod.GET, entity, ApiSoccerTeamList.class);
        return Objects.requireNonNull(response.getBody());
    }

    private HttpHeaders createHttpHeaders(String xAuthToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-auth-token", xAuthToken);
        return headers;
    }


}
