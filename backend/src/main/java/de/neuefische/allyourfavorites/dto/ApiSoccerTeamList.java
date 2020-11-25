package de.neuefische.allyourfavorites.dto;

import com.mongodb.client.model.Filters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiSoccerTeamList {

    private int count;
    private ApiFilter filters;
    private ApiCompetition competition;
    private ApiSeason season;
    private List<ApiSoccerTeam> teams;
}
