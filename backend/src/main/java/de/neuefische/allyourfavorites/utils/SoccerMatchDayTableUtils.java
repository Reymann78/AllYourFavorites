package de.neuefische.allyourfavorites.utils;

import de.neuefische.allyourfavorites.dto.ApiSoccerMatchDayTable;
import de.neuefische.allyourfavorites.dto.ApiSoccerMatchForMatchDayTable;
import de.neuefische.allyourfavorites.model.SoccerMatchDayTable;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class SoccerMatchDayTableUtils {

    public SoccerMatchDayTable buildSoccerMatchDayTable(ApiSoccerMatchDayTable apiSoccerMatchDayTable, String matchDay) {
        return new SoccerMatchDayTable(
                apiSoccerMatchDayTable.getCompetition().getId(),
                apiSoccerMatchDayTable.getCompetition().getName(),
                findCurrentMatchDay(apiSoccerMatchDayTable),
                matchDay,
                isMatchDayComplete(apiSoccerMatchDayTable, matchDay),
                findMatchesByMatchDay(apiSoccerMatchDayTable, matchDay)
        );
    }

    private List<ApiSoccerMatchForMatchDayTable> findMatchesByMatchDay(ApiSoccerMatchDayTable apiSoccerMatchDayTable, String matchDay) {
        List<ApiSoccerMatchForMatchDayTable> listOfSoccerMatchesByMatchDay = new ArrayList<>();
        List<ApiSoccerMatchForMatchDayTable> listOfAllSoccerMatches = apiSoccerMatchDayTable.getMatches();
        for(ApiSoccerMatchForMatchDayTable soccerMatch : listOfAllSoccerMatches) {
            if(soccerMatch.getMatchday().equals(matchDay)) {
                if(soccerMatch.getScore().getFullTime().getHomeTeam() == null) {
                    soccerMatch.getScore().getFullTime().setHomeTeam("-");
                    soccerMatch.getScore().getFullTime().setAwayTeam("-");
                }
                listOfSoccerMatchesByMatchDay.add(soccerMatch);
            }
        }
        return listOfSoccerMatchesByMatchDay;
    }

    private String findCurrentMatchDay(ApiSoccerMatchDayTable apiSoccerMatchDayTable) {
        List<ApiSoccerMatchForMatchDayTable> listOfAllSoccerMatches = apiSoccerMatchDayTable.getMatches();
        return listOfAllSoccerMatches.get(0).getSeason().getCurrentMatchday();
    }

    private boolean isMatchDayComplete(ApiSoccerMatchDayTable apiSoccerMatchDayTable, String matchDay) {
        List<ApiSoccerMatchForMatchDayTable> listOfAllSoccerMatches = apiSoccerMatchDayTable.getMatches();
        for(ApiSoccerMatchForMatchDayTable soccerMatch : listOfAllSoccerMatches) {
            if(soccerMatch.getMatchday().equals(matchDay) && !soccerMatch.getStatus().equals("FINISHED")) {
                return false;
            }
        }
        return true;
    }
}
