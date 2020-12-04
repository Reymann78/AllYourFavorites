package de.neuefische.allyourfavorites.db;

import de.neuefische.allyourfavorites.model.FavoriteMatches;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SoccerMatchesByTeamDb extends PagingAndSortingRepository<FavoriteMatches, String> {

    List<FavoriteMatches> findAll();
}
