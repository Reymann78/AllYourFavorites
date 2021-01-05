package de.neuefische.allyourfavorites.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoccerLeaguePosition {

    @Id
    private String teamId;
    private String position;
    private String crestUrl;
    private String name;
    private String playedGames;
    private String won;
    private String draw;
    private String lost;
    private String points;
    private String goalsFor;
    private String goalsAgainst;
    private String goalDifference;
}
