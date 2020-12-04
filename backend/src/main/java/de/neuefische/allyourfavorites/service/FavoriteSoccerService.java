package de.neuefische.allyourfavorites.service;

import de.neuefische.allyourfavorites.db.SoccerMatchesByTeamDb;
import de.neuefische.allyourfavorites.db.SoccerTeamDb;
import de.neuefische.allyourfavorites.db.UserDb;
import de.neuefische.allyourfavorites.model.FavoriteMatches;
import de.neuefische.allyourfavorites.model.SoccerTeam;
import de.neuefische.allyourfavorites.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FavoriteSoccerService {

    private final MongoTemplate mongoTemplate;
    private final SoccerTeamDb soccerTeamDb;
    private final UserDb userDb;
    private final SoccerMatchesByTeamDb soccerMatchesByTeamDb;

    @Autowired
    public FavoriteSoccerService(SoccerTeamDb soccerTeamDb, MongoTemplate mongoTemplate, UserDb userDb, SoccerMatchesByTeamDb soccerMatchesByTeamDb) {
        this.mongoTemplate = mongoTemplate;
        this.soccerTeamDb = soccerTeamDb;
        this.userDb = userDb;
        this.soccerMatchesByTeamDb = soccerMatchesByTeamDb;
    }

    public List<SoccerTeam> getListOfSoccerTeams() {
        return soccerTeamDb.findAll();
    }

    public List<String> getAllFavoritesOfUser(String principalName) {
        User user = userDb.findById(principalName).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return user.getFavorites();
    }

    public Iterable<FavoriteMatches> getAllMatchesOfFavorites(List<String> favorites) {
            return soccerMatchesByTeamDb.findAllById(favorites);
    }

    public String addFavoriteTeamId(String favoriteTeamId, String principalName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(principalName));

        Update update = new Update();
        update.addToSet("favorites", favoriteTeamId);

        mongoTemplate.updateFirst(query, update, User.class);

        return favoriteTeamId;
    }

    public void removeFavoriteTeamId(String favoriteTeamId, String principalName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(principalName));

        Update update = new Update();
        update.pull("favorites", favoriteTeamId);

        mongoTemplate.updateFirst(query, update, "user");
    }

}
