package de.neuefische.allyourfavorites.utils;

import de.neuefische.allyourfavorites.db.SoccerMatchesByTeamDb;
import de.neuefische.allyourfavorites.db.SoccerTeamDb;
import de.neuefische.allyourfavorites.model.Favorite;
import de.neuefische.allyourfavorites.model.SoccerMatch;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SoccerFavoriteUtils {

    private final SoccerTeamDb soccerTeamDb;
    private final SoccerMatchesByTeamDb soccerMatchesDb;
    private final SoccerMatchUtils soccerMatchUtils;

    public SoccerFavoriteUtils(SoccerTeamDb soccerTeamDb, SoccerMatchesByTeamDb soccerMatchesDb, SoccerMatchUtils soccerMatchUtils) {
        this.soccerTeamDb = soccerTeamDb;
        this.soccerMatchesDb = soccerMatchesDb;
        this.soccerMatchUtils = soccerMatchUtils;
    }

    public Favorite buildSoccerFavorite(List<SoccerMatch> formattedSoccerMatches, String teamId) {
        Favorite favorite = new Favorite();
        favorite.setTeamId(teamId);
        favorite.setName(soccerTeamDb.findSoccerTeamByTeamId(teamId).getName());
        favorite.setCrestUrl(soccerTeamDb.findSoccerTeamByTeamId(teamId).getCrestUrl());
        favorite.setLastMatch(soccerMatchUtils.getLastMatch(formattedSoccerMatches));
        favorite.setCurrentMatch(soccerMatchUtils.getCurrentMatch(formattedSoccerMatches));
        favorite.setNextMatch(soccerMatchUtils.getNextMatch(formattedSoccerMatches));
        soccerMatchesDb.save(favorite);
        return favorite;
    }

}
