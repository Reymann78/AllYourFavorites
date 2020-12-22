package de.neuefische.allyourfavorites.service;

import de.neuefische.allyourfavorites.db.UserDb;
import de.neuefische.allyourfavorites.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDb userDb;

    public UserService(UserDb userDb) {
        this.userDb = userDb;
    }

    public Iterable<User> getAllUsers() {
        return userDb.findAll();
    }

}
