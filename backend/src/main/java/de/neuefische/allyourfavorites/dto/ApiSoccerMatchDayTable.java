package de.neuefische.allyourfavorites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiSoccerMatchDayTable {

    private String count;
    private ApiFilter filters;
    private ApiCompetition competition;
    private List<ApiSoccerMatchForMatchDayTable> matches;
}
