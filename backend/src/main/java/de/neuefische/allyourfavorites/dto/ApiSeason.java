package de.neuefische.allyourfavorites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiSeason {
    private int id;
    private String startDate;
    private String endDate;
    private Integer currentMatchday;
    private String winner;

}
