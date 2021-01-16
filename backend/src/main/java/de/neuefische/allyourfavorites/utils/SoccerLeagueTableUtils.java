package de.neuefische.allyourfavorites.utils;

import de.neuefische.allyourfavorites.db.SoccerLeagueTableDb;
import de.neuefische.allyourfavorites.dto.ApiSoccerLeagueTable;
import de.neuefische.allyourfavorites.dto.ApiSoccerStandings;
import de.neuefische.allyourfavorites.dto.ApiSoccerTable;
import de.neuefische.allyourfavorites.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SoccerLeagueTableUtils {

    private final SoccerLeagueTableDb soccerLeagueTableDb;

    public SoccerLeagueTableUtils(SoccerLeagueTableDb soccerLeagueTableDb) {
        this.soccerLeagueTableDb = soccerLeagueTableDb;
    }

    public List<SoccerLeagueTable> buildSoccerLeagueTable(ApiSoccerLeagueTable apiSoccerLeagueTable) {
        List<SoccerLeagueTable> soccerLeagueTables = new ArrayList<>();

        for(ApiSoccerStandings standings : apiSoccerLeagueTable.standings) {

            List<SoccerLeaguePosition> soccerLeaguePositions = new ArrayList<>();

            for (ApiSoccerTable position : standings.table) {
                SoccerLeaguePosition soccerLeaguePosition = new SoccerLeaguePosition(
                        position.getTeam().getId(),
                        position.getPosition(),
                        position.getTeam().getCrestUrl(),
                        position.getTeam().getName(),
                        position.getPlayedGames(),
                        position.getWon(),
                        position.getDraw(),
                        position.getLost(),
                        position.getPoints(),
                        position.getGoalsFor(),
                        position.getGoalsAgainst(),
                        position.getGoalDifference());
                soccerLeaguePositions.add(soccerLeaguePosition);
            }

            SoccerLeagueTable soccerLeagueTable = new SoccerLeagueTable(
                    apiSoccerLeagueTable.getCompetition().getId(),
                    apiSoccerLeagueTable.getCompetition().getName(),
                    apiSoccerLeagueTable.getCompetition().getArea().getName(),
                    apiSoccerLeagueTable.getCompetition().getPlan(),
                    apiSoccerLeagueTable.getSeason().getCurrentMatchday(),
                    standings.getType(),
                    standings.getGroup(),
                    soccerLeaguePositions);
            soccerLeagueTables.add(soccerLeagueTable);
            soccerLeagueTableDb.save(soccerLeagueTable);
        }

        return soccerLeagueTables;
    }
}
