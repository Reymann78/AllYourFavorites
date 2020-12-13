package de.neuefische.allyourfavorites.controller;

import de.neuefische.allyourfavorites.model.User;
import de.neuefische.allyourfavorites.service.SignUpService;
import de.neuefische.allyourfavorites.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/auth/signup")
public class SignUpController {

    private final SignUpService signUpService;
    private final PasswordUtils passwordUtils;

    @Autowired
    public SignUpController(SignUpService signUpService, PasswordUtils passwordUtils) {
        this.signUpService = signUpService;
        this.passwordUtils = passwordUtils;
    }

    @PostMapping
    public String signUp(@RequestBody User user) {
        Optional<String> username = signUpService.signUp(user);
        if (username.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }
        if (!passwordUtils.validatePassword(user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Password not valid") {
            };
        }
        return username.get();
    }
}
