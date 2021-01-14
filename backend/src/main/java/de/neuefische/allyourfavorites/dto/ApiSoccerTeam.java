package de.neuefische.allyourfavorites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiSoccerTeam {
    private String id;
    private ApiCountry area;
    private String name;
    private String shortName;
    private String tla;
    private String crestUrl;
    private String address;
    private String phone;
    private String website;
    private String email;
    private String founded;
    private String clubColors;
    private String venue;
    private String lastUpdate;
    private ApiSoccerTeam homeTeam;
    private ApiSoccerTeam awayTeam;
    private ApiReferee referees;
}
