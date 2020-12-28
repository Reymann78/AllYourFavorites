package de.neuefische.allyourfavorites.controller;

import de.neuefische.allyourfavorites.dto.UserLoginDto;
import de.neuefische.allyourfavorites.security.JwtUtils;
import de.neuefische.allyourfavorites.service.ApiCrawler;
import de.neuefische.allyourfavorites.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

@RestController
@RequestMapping("auth/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final ApiCrawler apiCrawler;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, ApiCrawler apiCrawler) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.apiCrawler = apiCrawler;
    }

    @PostMapping
    public String login(@RequestBody UserLoginDto loginData) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginData.getUsername(), loginData.getPassword());

         try {
             authenticationManager.authenticate(authentication);
            } catch (AuthenticationException e) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "bad credentials");
            }
        apiCrawler.updateFavoritesInDb(apiCrawler.getAllTeamIdsOfUsersInDb());
        return jwtUtils.createToken(loginData.getUsername(), new HashMap<>());
    }
}
