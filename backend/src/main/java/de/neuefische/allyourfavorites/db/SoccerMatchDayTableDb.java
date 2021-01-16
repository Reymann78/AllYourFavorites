package de.neuefische.allyourfavorites.db;

import de.neuefische.allyourfavorites.model.SoccerMatchDayTable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SoccerMatchDayTableDb extends PagingAndSortingRepository<SoccerMatchDayTable, String> {

    SoccerMatchDayTable findSoccerMatchDayTableByCompetitionIdAndTableMatchDay(
        String competitionId,
        String matchDay);

}
