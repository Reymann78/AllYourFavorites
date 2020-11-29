package de.neuefische.allyourfavorites.controllerTests;

import de.neuefische.allyourfavorites.db.UserDb;
import de.neuefische.allyourfavorites.dto.UserLoginDto;
import de.neuefische.allyourfavorites.model.User;
import de.neuefische.allyourfavorites.service.SoccerTeamApiCrawler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"jwt.secretkey=sometoken"})
class LoginControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserDb userDb;

    private final String secretKey = "sometoken";

    @MockBean
    private SoccerTeamApiCrawler soccerTeamApiCrawler;

    @BeforeEach
    public void setupUser() {
        userDb.deleteAll();
        String password = new BCryptPasswordEncoder().encode("123456");
        userDb.save(new User("sven", password));
    }

    @Test
    public void loginWithValidCredentialsShouldReturnJwtToken() {

        //GIVEN
        UserLoginDto userloginDto = new UserLoginDto(
                "sven",
                "123456"
        );

        //WHEN
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/auth/login", userloginDto, String.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        String token = response.getBody();
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

        assertThat(claims.getSubject(), is("sven"));
        assertThat(claims.getExpiration().after(new Date()), is(true));

    }

    @Test
    public void loginWithInValidCredentialsShouldReturnForbidden() {

        //GIVEN
        UserLoginDto userloginDto = new UserLoginDto(
                "sven",
                "wrongPassword"
        );

        //WHEN
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/auth/login", userloginDto, String.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.FORBIDDEN));

    }

}

