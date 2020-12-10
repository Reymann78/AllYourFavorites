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
import java.time.Instant;
import java.util.*;

@Service
public class SoccerTeamApiCrawler {

    private final SoccerTeamDb soccerTeamDb;
    private final SoccerMatchesByTeamDb soccerMatchesDb;
    private final SoccerApiService soccerApiService;
    private final static String BUNDESLIGA = "2002";
    private final static String PREMIER_LEAGUE = "2021";
//    private final static String PRIMERA_DIVISION = "2014";
//    private final static String LIGUE_1 = "2015";
//    private final static String SERIE_A = "2019";
//    private final static String EREDIVISIE = "2003";
//    private final static String PRIMEIRA_LIGA = "2017";

    @Autowired
    public SoccerTeamApiCrawler(SoccerTeamDb soccerTeamDb, SoccerMatchesByTeamDb soccerMatchesDb, SoccerApiService soccerApiService) {
        this.soccerTeamDb = soccerTeamDb;
        this.soccerMatchesDb = soccerMatchesDb;
        this.soccerApiService = soccerApiService;
    }

    @PostConstruct
    private void postConstruct() {
        List<List<SoccerTeam>> ListOfLeagues = new ArrayList<>();
        List<SoccerTeam> bundesliga = getSoccerTeamsByCompetitionId(BUNDESLIGA);
        List<SoccerTeam> premierLeague = getSoccerTeamsByCompetitionId(PREMIER_LEAGUE);
//        List<SoccerTeam> primeraDivision = getSoccerTeamsByCompetitionId(PRIMERA_DIVISION);
//        List<SoccerTeam> lique1 = getSoccerTeamsByCompetitionId(LIGUE_1);
//        List<SoccerTeam> serieA = getSoccerTeamsByCompetitionId(SERIE_A);
//        List<SoccerTeam> eredivisie = getSoccerTeamsByCompetitionId(EREDIVISIE);
//        List<SoccerTeam> primeiraLiga = getSoccerTeamsByCompetitionId(PRIMEIRA_LIGA);
        ListOfLeagues.add(bundesliga);
        ListOfLeagues.add(premierLeague);
//        ListOfLeagues.add(primeraDivision);
//        ListOfLeagues.add(lique1);
//        ListOfLeagues.add(serieA);
//        ListOfLeagues.add(eredivisie);
//        ListOfLeagues.add(primeiraLiga);
        for(List<SoccerTeam> list : ListOfLeagues) {
            for (SoccerTeam soccerTeam : list) {
                soccerTeamDb.save(soccerTeam);
            }
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

    public Favorite getMatchesOfFavoriteByTeamId(String teamId) {
        List<ApiSoccerMatch> apiSoccerMatches = soccerApiService.getMatchesOfFavoriteByTeamId(teamId).getMatches();
        List<SoccerMatch> formattedSoccerMatches = buildSoccerMatch(apiSoccerMatches);
        Favorite favorite = new Favorite();
        favorite.setTeamId(teamId);
        favorite.setName(soccerTeamDb.findSoccerTeamByTeamId(teamId).getName());
        favorite.setCrestUrl(soccerTeamDb.findSoccerTeamByTeamId(teamId).getCrestUrl());
        favorite.setLastMatch(getLastMatch(formattedSoccerMatches));
        favorite.setCurrentMatch(getCurrentMatch(formattedSoccerMatches));
        favorite.setNextMatch(getNextMatch(formattedSoccerMatches));
        soccerMatchesDb.save(favorite);
        return favorite;
    }

    private SoccerMatch getLastMatch(List<SoccerMatch> soccerMatches) {
        List<SoccerMatch> finishedSoccerMatches = new ArrayList<>();
        for(SoccerMatch soccerMatch : soccerMatches) {
            if (soccerMatch.getStatus().equals("FINISHED")) {
                finishedSoccerMatches.add(soccerMatch);
            }
        }
        finishedSoccerMatches.sort(Comparator.comparing(SoccerMatch::getMatchDate));
        return finishedSoccerMatches.get(finishedSoccerMatches.size()-2);
    }

    private SoccerMatch getCurrentMatch(List<SoccerMatch> soccerMatches) {
        List<SoccerMatch> finishedSoccerMatches = new ArrayList<>();
        for(SoccerMatch soccerMatch : soccerMatches) {
            if (soccerMatch.getStatus().equals("FINISHED")) {
                finishedSoccerMatches.add(soccerMatch);
            }
        }
        finishedSoccerMatches.sort(Comparator.comparing(SoccerMatch::getMatchDate));
        return finishedSoccerMatches.get(finishedSoccerMatches.size()-1);
    }

    private SoccerMatch getNextMatch(List<SoccerMatch> soccerMatches) {
        List<SoccerMatch> scheduledSoccerMatches = new ArrayList<>();
        for(SoccerMatch soccerMatch : soccerMatches) {
            if (soccerMatch.getStatus().equals("SCHEDULED")) {
                scheduledSoccerMatches.add(soccerMatch);
            }
        }
        scheduledSoccerMatches.sort(Comparator.comparing(SoccerMatch::getMatchDate));
        return scheduledSoccerMatches.get(0);
    }

    private List<SoccerMatch> buildSoccerMatch(List<ApiSoccerMatch> apiSoccerMatches) {
        List<SoccerMatch> formattedSoccerMatches = new ArrayList<>();
        for(ApiSoccerMatch apiSoccerMatch : apiSoccerMatches) {
        String goalsHomeTeam = "-";
        String goalsAwayTeam = "-";
        String utcDate = apiSoccerMatch.getUtcDate();
        Instant formattedDate = Instant.parse(utcDate);
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
