package de.neuefische.allyourfavorites.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "soccerLeagueTable")
public class SoccerLeagueTable {

    private String competitionId;
    private String competitionName;
    private String country;
    private String plan;
    private String currentMatchDay;
    private String tableType;
    private String groupName;
    private List<SoccerLeaguePosition> positions;
}
