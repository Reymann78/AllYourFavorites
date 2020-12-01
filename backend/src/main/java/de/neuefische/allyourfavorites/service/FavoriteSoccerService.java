package de.neuefische.allyourfavorites.service;

import de.neuefische.allyourfavorites.db.SoccerTeamDb;
import de.neuefische.allyourfavorites.db.UserDb;
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

    private final SoccerTeamDb soccerTeamDb;
    private final MongoTemplate mongoTemplate;
    private final UserDb userDb;

    @Autowired
    public FavoriteSoccerService(SoccerTeamDb soccerTeamDb, MongoTemplate mongoTemplate, UserDb userDb) {
        this.soccerTeamDb = soccerTeamDb;
        this.mongoTemplate = mongoTemplate;
        this.userDb = userDb;
    }

    public List<SoccerTeam> getListOfSoccerTeams() {
        return soccerTeamDb.findAll();
    }

    public List<String> getAllFavoritesOfUser(String principalName) {
        User user = userDb.findById(principalName).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return user.getFavorites();
    }

    public String addFavorite(String nameOfFavorite, String principalName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(principalName));

        Update update = new Update();
        update.addToSet("favorites", nameOfFavorite);

        mongoTemplate.updateFirst(query, update, User.class);

        return nameOfFavorite;
    }

    public void removeFavorite(String teamName, String principalName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(principalName));

        Update update = new Update();
        update.pull("favorites", teamName);

        mongoTemplate.updateFirst(query, update, "user");
    }

}
