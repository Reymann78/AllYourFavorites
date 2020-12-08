package de.neuefische.allyourfavorites.db;

import de.neuefische.allyourfavorites.model.SoccerTeam;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SoccerTeamDb extends PagingAndSortingRepository<SoccerTeam, String> {

    List<SoccerTeam> findAll();
    SoccerTeam findSoccerTeamByTeamId(String teamId);
}
