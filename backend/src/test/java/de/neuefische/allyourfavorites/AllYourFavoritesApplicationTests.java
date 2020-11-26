package de.neuefische.allyourfavorites;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"x.Auth.Token=4599aaf68eaf463f9499272dda3d7591"
})
class AllYourFavoritesApplicationTests {

	@Test
	void contextLoads() {
	}

}
