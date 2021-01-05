package de.neuefische.allyourfavorites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiSoccerMatch {

    private String id;
    private ApiCompetition competition;
    private ApiSeason season;
    private String utcDate;
    private String status;
    private int matchDay;
    private String stage;
    private String group;
    private String lastUpdate;
    private ApiOdd odds;
    private ApiScore score;
    private ApiSoccerTeam homeTeam;
    private ApiSoccerTeam awayTeam;
    private List<ApiReferee> referees;
}
