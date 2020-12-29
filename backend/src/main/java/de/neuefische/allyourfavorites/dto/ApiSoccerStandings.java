package de.neuefische.allyourfavorites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiSoccerStandings {

    private String stage;
    private String type;
    private String group;
    public ApiSoccerTable[] table;
}
