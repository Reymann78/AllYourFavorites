package de.neuefische.allyourfavorites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiSeason {
    private int id;
    private Date startDate;
    private Date endDate;
    private int currentMatchday;
    private String winner;

}
