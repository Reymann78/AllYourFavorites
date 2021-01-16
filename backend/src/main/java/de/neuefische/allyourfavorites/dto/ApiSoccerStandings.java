package de.neuefische.allyourfavorites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiSoccerStandings {

    private String stage;
    private String type;
    private String group;
    public List<ApiSoccerTable> table;
}
