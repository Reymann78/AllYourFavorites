package de.neuefische.allyourfavorites.serviceTests;

import de.neuefische.allyourfavorites.db.SoccerTeamDb;
import de.neuefische.allyourfavorites.model.SoccerTeam;
import de.neuefische.allyourfavorites.service.FavoriteSoccerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

public class FavoriteSoccerServiceTest {

    final SoccerTeamDb soccerTeamDb = mock(SoccerTeamDb.class);
    final FavoriteSoccerService favoriteSoccerService = new FavoriteSoccerService(soccerTeamDb);

    @Test
    @DisplayName("The getListOfSoccerTeams method should return all soccer teams of the soccer db")
    void getListOfSoccerTeamsTest() {
        //GIVEN
        List<SoccerTeam> teamsInSoccerTeamDb = new ArrayList<>(List.of(
                new SoccerTeam("Borussia Dortmund"),
                new SoccerTeam("Bayern München"),
                new SoccerTeam("Eintracht Frankfurt")
                ));

        when(soccerTeamDb.findAll()).thenReturn(teamsInSoccerTeamDb);

        //When
        List<SoccerTeam> allTeams = favoriteSoccerService.getListOfSoccerTeams();

        //Then
        assertThat(allTeams, containsInAnyOrder(teamsInSoccerTeamDb.toArray()));
    }
}
