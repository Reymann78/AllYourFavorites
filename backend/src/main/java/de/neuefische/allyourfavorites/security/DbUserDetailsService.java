package de.neuefische.allyourfavorites.security;

import de.neuefische.allyourfavorites.db.UserDb;
import de.neuefische.allyourfavorites.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbUserDetailsService implements UserDetailsService {

    private final UserDb userRepository;

    public DbUserDetailsService(UserDb userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> matchingUser = userRepository.findById(username);
        if(matchingUser.isEmpty()){
            throw new UsernameNotFoundException("User with name " + username + " not found");
        }
        return new org.springframework.security.core.userdetails.User(username, matchingUser.get().getPassword(), List.of());
    }
}
