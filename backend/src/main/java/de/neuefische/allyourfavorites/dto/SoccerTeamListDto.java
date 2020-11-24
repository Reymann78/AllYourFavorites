package de.neuefische.allyourfavorites.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoccerTeamListDto {

    private int soccerCompetitionId;
    private List<SoccerTeam> soccerTeams;
}
