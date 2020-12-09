package de.neuefische.allyourfavorites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiScore {

    private String winner;
    private String duration;
    private ApiResult fullTime;
    private ApiResult halfTime;
    private ApiResult extraTime;
    private ApiResult penalties;
}
