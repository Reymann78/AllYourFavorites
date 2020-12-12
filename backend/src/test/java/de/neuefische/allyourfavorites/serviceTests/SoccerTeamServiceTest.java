package de.neuefische.allyourfavorites.serviceTests;

import de.neuefische.allyourfavorites.db.SoccerTeamDb;
import de.neuefische.allyourfavorites.model.SoccerTeam;
import de.neuefische.allyourfavorites.service.SoccerTeamService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

public class SoccerTeamServiceTest {

    final SoccerTeamDb soccerTeamDb = mock(SoccerTeamDb.class);
    final SoccerTeamService soccerTeamService = new SoccerTeamService(soccerTeamDb);

    @Test
    @DisplayName("The getListOfSoccerTeams method should return all soccer teams of the soccer db")
    void getListOfSoccerTeamsTest() {
        //GIVEN
        List <SoccerTeam> listOfTeamsInSoccerTeamDb = new ArrayList<>(List.of(
                new SoccerTeam("1","Borussia Dortmund", "bvbUrl", "Bundesliga"),
                new SoccerTeam("2", "Bayern München", "fcbUrl", "Bundesliga"),
                new SoccerTeam("3", "Eintracht Frankfurt", "sgeUrl", "Bundesliga")
        ));

        when(soccerTeamDb.findAll()).thenReturn(listOfTeamsInSoccerTeamDb);

        //When
        List<SoccerTeam> allTeams = soccerTeamService.getListOfSoccerTeams();

        //Then
        assertThat(allTeams, containsInAnyOrder(listOfTeamsInSoccerTeamDb.toArray()));
    }
}
