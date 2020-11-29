package de.neuefische.allyourfavorites;

import de.neuefische.allyourfavorites.service.SoccerTeamApiCrawler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"jwt.secretkey=sometoken"})
class AllYourFavoritesApplicationTests {

	@MockBean
	private SoccerTeamApiCrawler soccerTeamApiCrawler;

	@Test
	void contextLoads() {
	}

}
