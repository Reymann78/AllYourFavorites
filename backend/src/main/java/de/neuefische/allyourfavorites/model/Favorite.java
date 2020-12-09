package de.neuefische.allyourfavorites.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "soccerMatchesByTeam")
public class Favorite {
    @Id
    private String teamId;
    private String name;
    private String crestUrl;
    private SoccerMatch lastMatch;
    private SoccerMatch currentMatch;
    private SoccerMatch nextMatch;

}
