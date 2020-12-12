package de.neuefische.allyourfavorites.utils;

import de.neuefische.allyourfavorites.dto.ApiSoccerMatch;
import de.neuefische.allyourfavorites.model.SoccerMatch;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class SoccerMatchUtils {

    public List<SoccerMatch> buildSoccerMatch(List<ApiSoccerMatch> apiSoccerMatches) {
        List<SoccerMatch> formattedSoccerMatches = new ArrayList<>();
        for(ApiSoccerMatch apiSoccerMatch : apiSoccerMatches) {
            String[] goalsOfTeams = getGoalsOfTeams(apiSoccerMatch);
            String goalsHomeTeam = goalsOfTeams[0];
            String goalsAwayTeam = goalsOfTeams[1];
            SoccerMatch formattedSoccerMatch = new SoccerMatch(
                    apiSoccerMatch.getId(),
                    apiSoccerMatch.getMatchday(),
                    formateUtcDate(apiSoccerMatch.getUtcDate()),
                    apiSoccerMatch.getStatus(),
                    apiSoccerMatch.getCompetition().getId(),
                    apiSoccerMatch.getCompetition().getName(),
                    apiSoccerMatch.getHomeTeam(),
                    apiSoccerMatch.getAwayTeam(),
                    goalsHomeTeam,
                    goalsAwayTeam);
            formattedSoccerMatches.add(formattedSoccerMatch);
        }
        return formattedSoccerMatches;
    }

    private Instant formateUtcDate (String utcDate) {
        return Instant.parse(utcDate);
    }

    private String[] getGoalsOfTeams(ApiSoccerMatch apiSoccerMatch) {
        String[] goalsOfTeams = new String[2];
        goalsOfTeams[0] = "-";
        goalsOfTeams[1] = "-";
        if (apiSoccerMatch.getStatus().equals("FINISHED")) {
            goalsOfTeams[0] = apiSoccerMatch.getScore().getFullTime().getHomeTeam();
            goalsOfTeams[1] = apiSoccerMatch.getScore().getFullTime().getAwayTeam();
        }
        return goalsOfTeams;
    }

    public SoccerMatch getLastMatch(List<SoccerMatch> soccerMatches) {
        List<SoccerMatch> finishedSoccerMatches = new ArrayList<>();
        for(SoccerMatch soccerMatch : soccerMatches) {
            if (soccerMatch.getStatus().equals("FINISHED")) {
                finishedSoccerMatches.add(soccerMatch);
            }
        }
        finishedSoccerMatches.sort(Comparator.comparing(SoccerMatch::getMatchDate));
        return finishedSoccerMatches.get(finishedSoccerMatches.size()-2);
    }

    public SoccerMatch getCurrentMatch(List<SoccerMatch> soccerMatches) {
        List<SoccerMatch> finishedSoccerMatches = new ArrayList<>();
        for(SoccerMatch soccerMatch : soccerMatches) {
            if (soccerMatch.getStatus().equals("FINISHED")) {
                finishedSoccerMatches.add(soccerMatch);
            }
        }
        finishedSoccerMatches.sort(Comparator.comparing(SoccerMatch::getMatchDate));
        return finishedSoccerMatches.get(finishedSoccerMatches.size()-1);
    }

    public SoccerMatch getNextMatch(List<SoccerMatch> soccerMatches) {
        List<SoccerMatch> scheduledSoccerMatches = new ArrayList<>();
        for(SoccerMatch soccerMatch : soccerMatches) {
            if (soccerMatch.getStatus().equals("SCHEDULED") || soccerMatch.getStatus().equals("IN_PLAY")) {
                scheduledSoccerMatches.add(soccerMatch);
            }
        }
        scheduledSoccerMatches.sort(Comparator.comparing(SoccerMatch::getMatchDate));
        return scheduledSoccerMatches.get(0);
    }

}