package de.neuefische.allyourfavorites.service;

import de.neuefische.allyourfavorites.db.SoccerTeamDb;
import de.neuefische.allyourfavorites.model.SoccerTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteSoccerService {

    private final SoccerTeamDb soccerTeamDb;

    @Autowired
    public FavoriteSoccerService( SoccerTeamDb soccerTeamDb) {
        this.soccerTeamDb = soccerTeamDb;
    }

    public List<SoccerTeam> getListOfSoccerTeams() {
        return soccerTeamDb.findAll();
    }


}
