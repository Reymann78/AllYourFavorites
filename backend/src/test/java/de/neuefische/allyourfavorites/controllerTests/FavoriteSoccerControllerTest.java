package de.neuefische.allyourfavorites.controllerTests;

import de.neuefische.allyourfavorites.model.SoccerTeam;
import de.neuefische.allyourfavorites.service.FavoriteSoccerService;
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

    private String getSoccerTeamsUrl() {
        return "http://localhost:" + port + "/favorites/soccerTeams";
    }

    @Test
    public void getMappingTest() {
        //GIVEN
        List<SoccerTeam> teamsInSoccerTeamDb = new ArrayList<>(List.of(
                new SoccerTeam(1,"Borussia Dortmund", "bvbUrl"),
                new SoccerTeam(2, "Bayern München", "fcbUrl"),
                new SoccerTeam(3, "Eintracht Frankfurt", "sgeURl")
        ));
        String url = getSoccerTeamsUrl();
        when(favoriteSoccerService.getListOfSoccerTeams()).thenReturn(teamsInSoccerTeamDb);

        //WHEN
        ResponseEntity<SoccerTeam[]> response = restTemplate.getForEntity(url, SoccerTeam[].class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(teamsInSoccerTeamDb.toArray()));
    }

}

