package de.neuefische.allyourfavorites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableParamsDto {

    private String competitionId;
    private String matchDay;
    private String groupName;
    private String tableType;

}
