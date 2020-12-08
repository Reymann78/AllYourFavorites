package de.neuefische.allyourfavorites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult {

    private String homeTeam;
    private String awayTeam;
}
