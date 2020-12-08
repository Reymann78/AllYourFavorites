package de.neuefische.allyourfavorites.service;

import de.neuefische.allyourfavorites.apiService.SoccerApiService;
import de.neuefische.allyourfavorites.db.SoccerMatchesByTeamDb;
import de.neuefische.allyourfavorites.db.SoccerTeamDb;
import de.neuefische.allyourfavorites.dto.*;
import de.neuefische.allyourfavorites.model.Favorite;
import de.neuefische.allyourfavorites.model.SoccerMatch;
import de.neuefische.allyourfavorites.model.SoccerTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SoccerTeamApiCrawler {

    private final SoccerTeamDb soccerTeamDb;
    private final SoccerMatchesByTeamDb soccerMatchesDb;
    private final SoccerApiService soccerApiService;
    private final static String BUNDESLIGA = "2002";

    @Autowired
    public SoccerTeamApiCrawler(SoccerTeamDb soccerTeamDb, SoccerMatchesByTeamDb soccerMatchesDb, SoccerApiService soccerApiService) {
        this.soccerTeamDb = soccerTeamDb;
        this.soccerMatchesDb = soccerMatchesDb;
        this.soccerApiService = soccerApiService;
    }

    @PostConstruct
    private void postConstruct() {
        List<SoccerTeam> list = getSoccerTeamsByCompetitionId(BUNDESLIGA);
        for(SoccerTeam soccerTeam : list) {
            soccerTeamDb.save(soccerTeam);
        }
    }

    public List<SoccerTeam> getSoccerTeamsByCompetitionId(String competitionId) {
        List<SoccerTeam> listOfSoccerTeams = new ArrayList<>();
        ApiSoccerTeamList apiSoccerTeams = soccerApiService.getSoccerTeamsByCompetitionId(competitionId);
        String competitionName = apiSoccerTeams.getCompetition().getName();

        for (ApiSoccerTeam apiSoccerTeam : apiSoccerTeams.getTeams()) {
            SoccerTeam soccerTeam = new SoccerTeam(
                    apiSoccerTeam.getId(),
                    apiSoccerTeam.getName(),
                    apiSoccerTeam.getCrestUrl(),
                    competitionName);
            listOfSoccerTeams.add(soccerTeam);
        }

        return listOfSoccerTeams;
    }

    public Favorite getMatchesOfFavoriteByTeamId(String teamId) throws ParseException {
        List<ApiSoccerMatch> apiSoccerMatches = soccerApiService.getMatchesOfFavoriteByTeamId(teamId).getMatches();
        List<SoccerMatch> formattedSoccerMatches = buildSoccerMatch(apiSoccerMatches);
        Favorite favorite = new Favorite();
        favorite.setTeamId(teamId);
        favorite.setName(soccerTeamDb.findSoccerTeamByTeamId(teamId).getName());
        favorite.setCrestUrl(soccerTeamDb.findSoccerTeamByTeamId(teamId).getCrestUrl());
        List<SoccerMatch> currentSoccerMatches = findCurrentMatches(formattedSoccerMatches);
        //assert currentSoccerMatches != null;
        favorite.setLastMatch(currentSoccerMatches.get(0));
        favorite.setCurrentMatch(currentSoccerMatches.get(1));
        favorite.setNextMatch(currentSoccerMatches.get(2));
        soccerMatchesDb.save(favorite);
        return favorite;
    }

    private List<SoccerMatch> findCurrentMatches(List<SoccerMatch> soccerMatches) {
        List<SoccerMatch> currentSoccerMatches = new ArrayList<>();
        List<SoccerMatch> finishedSoccerMatches = new ArrayList<>();
        List<SoccerMatch> scheduledSoccerMatches = new ArrayList<>();
        for(SoccerMatch soccerMatch : soccerMatches) {
            if(soccerMatch.getStatus().equals("FINISHED")) {
                finishedSoccerMatches.add(soccerMatch);
            }
            if(soccerMatch.getStatus().equals("SCHEDULED")) {
                scheduledSoccerMatches.add(soccerMatch);
            }
        }
        finishedSoccerMatches.sort(Comparator.comparing(SoccerMatch::getMatchDate));
        scheduledSoccerMatches.sort(Comparator.comparing(SoccerMatch::getMatchDate));
        currentSoccerMatches.add(finishedSoccerMatches.get(finishedSoccerMatches.size()-2));
        currentSoccerMatches.add(finishedSoccerMatches.get(finishedSoccerMatches.size()-1));
        currentSoccerMatches.add(scheduledSoccerMatches.get(0));
    return currentSoccerMatches;
    }

    private List<SoccerMatch> buildSoccerMatch(List<ApiSoccerMatch> apiSoccerMatches) throws ParseException {
        List<SoccerMatch> formattedSoccerMatches = new ArrayList<>();
        for(ApiSoccerMatch apiSoccerMatch : apiSoccerMatches) {
        String goalsHomeTeam = "-";
        String goalsAwayTeam = "-";
        String utcDate = apiSoccerMatch.getUtcDate();
        SimpleDateFormat dateFormatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date formattedDate = dateFormatted.parse(utcDate);
        if(apiSoccerMatch.getStatus().equals("FINISHED")) {
            goalsHomeTeam = apiSoccerMatch.getScore().getFullTime().getHomeTeam();
            goalsAwayTeam = apiSoccerMatch.getScore().getFullTime().getAwayTeam();
        }
        SoccerMatch formattedSoccerMatch = new SoccerMatch(
            apiSoccerMatch.getId(),
            apiSoccerMatch.getMatchday(),
            formattedDate,
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

}
