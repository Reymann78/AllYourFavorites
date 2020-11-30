package de.neuefische.allyourfavorites.service;

import de.neuefische.allyourfavorites.db.SoccerTeamDb;
import de.neuefische.allyourfavorites.model.SoccerTeam;
import de.neuefische.allyourfavorites.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteSoccerService {

    private final SoccerTeamDb soccerTeamDb;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public FavoriteSoccerService(SoccerTeamDb soccerTeamDb, MongoTemplate mongoTemplate) {
        this.soccerTeamDb = soccerTeamDb;
        this.mongoTemplate = mongoTemplate;
    }

    public List<SoccerTeam> getListOfSoccerTeams() {
        return soccerTeamDb.findAll();
    }


    public String add(String nameOfFavorite, String principalName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(principalName));

        Update update = new Update();
        update.addToSet("favorites", nameOfFavorite);

        mongoTemplate.updateFirst(query, update, User.class);

        return nameOfFavorite;
    }
}
