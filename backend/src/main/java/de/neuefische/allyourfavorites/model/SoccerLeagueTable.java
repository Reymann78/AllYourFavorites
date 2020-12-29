package de.neuefische.allyourfavorites.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "soccerLeagueTable")
public class SoccerLeagueTable {

    @Id
    private String competitionId;
    private String competitionName;
    private String country;
    private String plan;
    private int currentMatchday;
    private List<SoccerLeaguePosition> positions;
}
