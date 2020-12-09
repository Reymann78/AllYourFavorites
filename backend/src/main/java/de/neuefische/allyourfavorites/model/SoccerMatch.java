package de.neuefische.allyourfavorites.model;

import de.neuefische.allyourfavorites.dto.ApiSoccerTeam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoccerMatch {
    private String matchId;
    private int matchDay;
    private Instant matchDate;
    private String status;
    private int competitionId;
    private String competitionName;
    private ApiSoccerTeam homeTeam;
    private ApiSoccerTeam awayTeam;
    private String homeTeamGoals;
    private String awayTeamGoals;
}
