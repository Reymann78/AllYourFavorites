package de.neuefische.allyourfavorites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiSoccerLeagueTable {

    private ApiFilter filters;
    private ApiCompetition competition;
    private ApiSeason season;
    public List<ApiSoccerStandings> standings;
}
