package de.neuefische.allyourfavorites.model;

import de.neuefische.allyourfavorites.dto.ApiSoccerMatchForMatchDayTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoccerMatchDayTable {

    private String competitionId;
    private String competitionName;
    private String currentMatchDay;
    private String tableMatchDay;
    private Boolean isMatchDayComplete;
    private List<ApiSoccerMatchForMatchDayTable> matches;
}
