package de.neuefische.allyourfavorites.service;

import de.neuefische.allyourfavorites.db.UserDb;
import de.neuefische.allyourfavorites.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SignUpService {

    private final UserDb userDb;

    public SignUpService(UserDb userDb) {
        this.userDb = userDb;
    }

    public Optional<String> signUp(User newUser) {
        Optional<User> user = userDb.findById(newUser.getUsername());
        if (user.isPresent()) {
            return Optional.empty();
        }
        String hashPassword = new BCryptPasswordEncoder().encode(newUser.getPassword());
        User userWithPassword = new User(
                newUser.getUsername(),
                hashPassword,
                newUser.getEmail(),
                new ArrayList<>(List.of()));

        userDb.save(userWithPassword);
        return Optional.of(newUser.getUsername());
    }
}
