package de.neuefische.allyourfavorites.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoccerMatch {
    private String matchId;
    private int matchDay;
    private Date matchDate;
    private String competitionId;
    private String competitionName;
    private String homeTeam;
    private String awayTeam;
    private String homeTeamGoals;
    private String awayTeamGoals;
}
