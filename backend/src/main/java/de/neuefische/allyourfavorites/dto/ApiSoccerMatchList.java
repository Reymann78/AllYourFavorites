package de.neuefische.allyourfavorites.dto;

import com.mongodb.client.model.Filters;
import de.neuefische.allyourfavorites.apiService.SoccerApiService;
import de.neuefische.allyourfavorites.model.SoccerMatch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiSoccerMatchList {

    private int count;
    private ApiFilter filters;
    private List<ApiSoccerMatch> matches;
}
