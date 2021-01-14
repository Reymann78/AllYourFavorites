package de.neuefische.allyourfavorites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiSoccerMatchForMatchDayTable {

    private String id;
    private ApiSeason season;
    private String utcDate;
    private String status;
    private String matchday;
    private String stage;
    private String group;
    private String lastUpdated;
    private ApiOdd odds;
    private ApiScore score;
}
