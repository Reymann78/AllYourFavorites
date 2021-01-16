package de.neuefische.allyourfavorites.dto;

import de.neuefische.allyourfavorites.model.SoccerTeam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiSoccerMatchForMatchDayTable {

    private String id;
    private ApiSeason season;
    private Instant utcDate;
    private String status;
    private String matchday;
    private String stage;
    private String group;
    private String lastUpdated;
    private ApiOdd odds;
    private ApiScore score;
    private SoccerTeam homeTeam;
    private SoccerTeam awayTeam;
    private List<ApiReferee> referees;
}
