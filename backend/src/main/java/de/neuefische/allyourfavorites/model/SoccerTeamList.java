package de.neuefische.allyourfavorites.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "soccerTeams")
public class SoccerTeamList {
    @Id
    private int competitionId;
    private List<SoccerTeam> soccerTeams;
}
