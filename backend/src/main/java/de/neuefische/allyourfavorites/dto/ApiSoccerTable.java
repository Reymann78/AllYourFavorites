package de.neuefische.allyourfavorites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiSoccerTable {

    private String position;
    private ApiSoccerTeam team;
    private String playedGames;
    private String form;
    private String won;
    private String draw;
    private String lost;
    private String points;
    private String goalsFor;
    private String goalsAgainst;
    private String goalDifference;

}
