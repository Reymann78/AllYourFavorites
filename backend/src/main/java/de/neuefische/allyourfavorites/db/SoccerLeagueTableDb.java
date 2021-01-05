package de.neuefische.allyourfavorites.db;

import de.neuefische.allyourfavorites.model.SoccerLeagueTable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface SoccerLeagueTableDb extends PagingAndSortingRepository<SoccerLeagueTable, String> {

    SoccerLeagueTable findSoccerLeagueTableByCompetitionIdAndCurrentMatchDayAndGroupNameAndTableType(
            String competitionId,
            String matchDay,
            String groupName,
            String tableType);
}