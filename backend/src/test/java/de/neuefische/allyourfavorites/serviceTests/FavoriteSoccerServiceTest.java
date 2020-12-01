package de.neuefische.allyourfavorites.serviceTests;

import de.neuefische.allyourfavorites.db.SoccerTeamDb;
import de.neuefische.allyourfavorites.db.UserDb;
import de.neuefische.allyourfavorites.model.SoccerTeam;
import de.neuefische.allyourfavorites.service.FavoriteSoccerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

public class FavoriteSoccerServiceTest {

    final SoccerTeamDb soccerTeamDb = mock(SoccerTeamDb.class);
    final MongoTemplate mongoTemplate = mock(MongoTemplate.class);
    final UserDb userDb = mock(UserDb.class);
    final FavoriteSoccerService favoriteSoccerService = new FavoriteSoccerService(soccerTeamDb, mongoTemplate, userDb);

    @Test
    @DisplayName("The getListOfSoccerTeams method should return all soccer teams of the soccer db")
    void getListOfSoccerTeamsTest() {
        //GIVEN
        List <SoccerTeam> listOfTeamsInSoccerTeamDb = new ArrayList<>(List.of(
                new SoccerTeam(1,"Borussia Dortmund", "bvbUrl", "Bundesliga"),
                new SoccerTeam(2, "Bayern München", "fcbUrl", "Bundesliga"),
                new SoccerTeam(3, "Eintracht Frankfurt", "sgeUrl", "Bundesliga")
        ));

        when(soccerTeamDb.findAll()).thenReturn(listOfTeamsInSoccerTeamDb);

        //When
        List<SoccerTeam> allTeams = favoriteSoccerService.getListOfSoccerTeams();

        //Then
        assertThat(allTeams, containsInAnyOrder(listOfTeamsInSoccerTeamDb.toArray()));
    }
}
