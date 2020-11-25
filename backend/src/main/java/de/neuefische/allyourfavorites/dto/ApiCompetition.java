package de.neuefische.allyourfavorites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiCompetition {
    private int id;
    private ApiCountry area;
    private String name;
    private String code;
    private String plan;
    private String lastUpdate;
}
