package de.neuefische.allyourfavorites.controllerTests;

import de.neuefische.allyourfavorites.model.SoccerTeam;
import de.neuefische.allyourfavorites.service.FavoriteSoccerService;
import de.neuefische.allyourfavorites.service.SoccerTeamApiCrawler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FavoriteSoccerControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private FavoriteSoccerService favoriteSoccerService;

    @MockBean
    private SoccerTeamApiCrawler soccerTeamApiCrawler;

    private String getSoccerTeamsUrl() {
        return "http://localhost:" + port + "/favorites/soccerTeams";
    }

    @Test
    public void getMappingTest() {
        //GIVEN
        List <SoccerTeam> listOfTeamsInSoccerTeamDb = new ArrayList<>(List.of(
                new SoccerTeam(1,"Borussia Dortmund", "bvbUrl", "Bundesliga"),
                new SoccerTeam(2, "Bayern München", "fcbUrl", "Bundesliga"),
                new SoccerTeam(3, "Eintracht Frankfurt", "sgeUrl", "Bundesliga")
        ));

        String url = getSoccerTeamsUrl();
        when(favoriteSoccerService.getListOfSoccerTeams()).thenReturn(listOfTeamsInSoccerTeamDb);

        //WHEN
        ResponseEntity<SoccerTeam[]> response = restTemplate.getForEntity(url, SoccerTeam[].class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(listOfTeamsInSoccerTeamDb.toArray()));
    }

}

