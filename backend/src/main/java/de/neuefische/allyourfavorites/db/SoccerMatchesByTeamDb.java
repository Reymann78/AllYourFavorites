package de.neuefische.allyourfavorites.db;

import de.neuefische.allyourfavorites.model.Favorite;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SoccerMatchesByTeamDb extends PagingAndSortingRepository<Favorite, String> {

    List<Favorite> findAll();
}
