package de.neuefische.allyourfavorites.utils;

import de.neuefische.allyourfavorites.db.SoccerLeagueTableDb;
import de.neuefische.allyourfavorites.dto.ApiSoccerLeagueTable;
import de.neuefische.allyourfavorites.model.SoccerLeaguePosition;
import de.neuefische.allyourfavorites.model.SoccerLeagueTable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SoccerLeagueTableUtils {

    private final SoccerLeagueTableDb soccerLeagueTableDb;

    public SoccerLeagueTableUtils(SoccerLeagueTableDb soccerLeagueTableDb) {
        this.soccerLeagueTableDb = soccerLeagueTableDb;
    }

    public SoccerLeagueTable buildSoccerLeagueTable(ApiSoccerLeagueTable apiSoccerLeagueTable) {
        List<SoccerLeaguePosition> soccerLeaguePositions = new ArrayList<>();
        String competitionId = apiSoccerLeagueTable.getCompetition().getId();
        String competitionName = apiSoccerLeagueTable.getCompetition().getName();
        String countryName = apiSoccerLeagueTable.getCompetition().getArea().getName();
        String round = apiSoccerLeagueTable.getCompetition().getPlan();
        int currentMatchday = apiSoccerLeagueTable.getSeason().getCurrentMatchday();

        for (int i = 0; i < apiSoccerLeagueTable.standings[0].table.length; i++) {
            SoccerLeaguePosition soccerLeaguePosition = new SoccerLeaguePosition(
                    apiSoccerLeagueTable.standings[0].table[i].getPosition(),
                    apiSoccerLeagueTable.standings[0].table[i].getTeam().getCrestUrl(),
                    apiSoccerLeagueTable.standings[0].table[i].getTeam().getName(),
                    apiSoccerLeagueTable.standings[0].table[i].getPlayedGames(),
                    apiSoccerLeagueTable.standings[0].table[i].getWon(),
                    apiSoccerLeagueTable.standings[0].table[i].getDraw(),
                    apiSoccerLeagueTable.standings[0].table[i].getLost(),
                    apiSoccerLeagueTable.standings[0].table[i].getPoints(),
                    apiSoccerLeagueTable.standings[0].table[i].getGoalsFor(),
                    apiSoccerLeagueTable.standings[0].table[i].getGoalsAgainst(),
                    apiSoccerLeagueTable.standings[0].table[i].getGoalDifference());
            soccerLeaguePositions.add(soccerLeaguePosition);
        }

        SoccerLeagueTable soccerLeagueTable = new SoccerLeagueTable(competitionId,
                competitionName,
                countryName,
                round,
                currentMatchday,
                soccerLeaguePositions);

        soccerLeagueTableDb.save(soccerLeagueTable);
        return soccerLeagueTable;
    }
}
