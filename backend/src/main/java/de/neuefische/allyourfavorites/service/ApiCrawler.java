package de.neuefische.allyourfavorites.service;

import de.neuefische.allyourfavorites.apiService.SoccerApiService;
import de.neuefische.allyourfavorites.db.SoccerTeamDb;
import de.neuefische.allyourfavorites.dto.*;
import de.neuefische.allyourfavorites.model.Favorite;
import de.neuefische.allyourfavorites.model.SoccerMatch;
import de.neuefische.allyourfavorites.model.SoccerTeam;
import de.neuefische.allyourfavorites.model.User;
import de.neuefische.allyourfavorites.utils.SoccerFavoriteUtils;
import de.neuefische.allyourfavorites.utils.SoccerMatchUtils;
import de.neuefische.allyourfavorites.utils.SoccerTeamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class ApiCrawler {

    private final SoccerTeamDb soccerTeamDb;
    private final SoccerApiService soccerApiService;
    private final UserService userService;
    private final SoccerTeamUtils soccerTeamUtils;
    private final SoccerFavoriteUtils soccerFavoriteUtils;
    private final SoccerMatchUtils soccerMatchUtils;
    private final static String BUNDESLIGA = "2002";
    private final static String PREMIER_LEAGUE = "2021";
    private final static String PRIMERA_DIVISION = "2014";
    private final static String LIGUE_1 = "2015";
    private final static String SERIE_A = "2019";
    private final static String EREDIVISIE = "2003";
    private final static String PRIMEIRA_LIGA = "2017";

    @Autowired
    public ApiCrawler(SoccerTeamDb soccerTeamDb, SoccerApiService soccerApiService, UserService userService, SoccerTeamUtils soccerTeamUtils, SoccerFavoriteUtils soccerFavoriteUtils, SoccerMatchUtils soccerMatchUtils) {
        this.soccerTeamDb = soccerTeamDb;
        this.soccerApiService = soccerApiService;
        this.userService = userService;
        this.soccerTeamUtils = soccerTeamUtils;
        this.soccerFavoriteUtils = soccerFavoriteUtils;
        this.soccerMatchUtils = soccerMatchUtils;
    }

    @PostConstruct
    private void postConstruct() {
        List<List<SoccerTeam>> ListOfLeagues = new ArrayList<>();
        List<SoccerTeam> bundesliga = getSoccerTeamsByCompetitionId(BUNDESLIGA);
        List<SoccerTeam> premierLeague = getSoccerTeamsByCompetitionId(PREMIER_LEAGUE);
        List<SoccerTeam> primeraDivision = getSoccerTeamsByCompetitionId(PRIMERA_DIVISION);
        List<SoccerTeam> lique1 = getSoccerTeamsByCompetitionId(LIGUE_1);
        List<SoccerTeam> serieA = getSoccerTeamsByCompetitionId(SERIE_A);
        List<SoccerTeam> eredivisie = getSoccerTeamsByCompetitionId(EREDIVISIE);
        List<SoccerTeam> primeiraLiga = getSoccerTeamsByCompetitionId(PRIMEIRA_LIGA);
        ListOfLeagues.add(bundesliga);
        ListOfLeagues.add(premierLeague);
        ListOfLeagues.add(primeraDivision);
        ListOfLeagues.add(lique1);
        ListOfLeagues.add(serieA);
        ListOfLeagues.add(eredivisie);
        ListOfLeagues.add(primeiraLiga);
        for(List<SoccerTeam> list : ListOfLeagues) {
            for (SoccerTeam soccerTeam : list) {
                soccerTeamDb.save(soccerTeam);
            }
        }
    }

    @Scheduled(cron = "0 30/60 * * * ?")
    private void scheduled() {
        Set<String> teamIds = getAllTeamIdsOfUsersInDb();
        updateFavoritesInDb(teamIds);
    }


    public List<SoccerTeam> getSoccerTeamsByCompetitionId(String competitionId) {
        ApiSoccerTeamList apiSoccerTeams = soccerApiService.getSoccerTeamsByCompetitionId(competitionId);
        return soccerTeamUtils.getSoccerTeamsByCompetitionId(apiSoccerTeams);
    }

    public Favorite getMatchesOfFavoriteByTeamId(String teamId) {
        List<ApiSoccerMatch> apiSoccerMatches = soccerApiService.getMatchesOfFavoriteByTeamId(teamId).getMatches();
        List<SoccerMatch> formattedSoccerMatches = soccerMatchUtils.buildSoccerMatch(apiSoccerMatches);
        return soccerFavoriteUtils.buildSoccerFavorite(formattedSoccerMatches, teamId);
    }

    public Set<String> getAllTeamIdsOfUsersInDb() {
        Iterable<User> users = userService.getAllUsers();
        Set<String> allTeamIds = new HashSet<>();
        for(User user : users) {
            List<String> teamIds = (user.getFavorites());
            allTeamIds.addAll(teamIds);
        }
        return allTeamIds;
    }

    public void updateFavoritesInDb(Set<String> teamIds) {
        for(String teamId : teamIds) {
            getMatchesOfFavoriteByTeamId(teamId);
        }
    }

}
