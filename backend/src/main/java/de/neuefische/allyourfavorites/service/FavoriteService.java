package de.neuefische.allyourfavorites.service;

import de.neuefische.allyourfavorites.db.SoccerMatchesByTeamDb;
import de.neuefische.allyourfavorites.db.UserDb;
import de.neuefische.allyourfavorites.model.Favorite;
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
import java.util.Optional;

@Service
public class FavoriteService {

    private final MongoTemplate mongoTemplate;
    private final UserDb userDb;
    private final SoccerMatchesByTeamDb soccerMatchesByTeamDb;
    private final ApiCrawler apiCrawler;

    @Autowired
    public FavoriteService(MongoTemplate mongoTemplate, UserDb userDb, SoccerMatchesByTeamDb soccerMatchesByTeamDb, ApiCrawler apiCrawler) {
        this.mongoTemplate = mongoTemplate;
        this.userDb = userDb;
        this.soccerMatchesByTeamDb = soccerMatchesByTeamDb;
        this.apiCrawler = apiCrawler;
    }

    public List<String> getAllFavoritesOfUser(String principalName) {
        User user = userDb.findById(principalName).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return user.getFavorites();
    }

    public Iterable<Favorite> getAllMatchesOfFavorites(List<String> favorites) {
            return soccerMatchesByTeamDb.findAllById(favorites);
    }

    public Optional<User> addFavoriteTeamId(String favoriteTeamId, String principalName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(principalName));

        Update update = new Update();
        update.addToSet("favorites", favoriteTeamId);

        mongoTemplate.updateFirst(query, update, User.class);
        apiCrawler.getMatchesOfFavoriteByTeamId(favoriteTeamId);
        return userDb.findById(principalName);
    }

    public void removeFavoriteTeamId(String favoriteTeamId, String principalName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(principalName));

        Update update = new Update();
        update.pull("favorites", favoriteTeamId);

        mongoTemplate.updateFirst(query, update, "user");
    }

}
