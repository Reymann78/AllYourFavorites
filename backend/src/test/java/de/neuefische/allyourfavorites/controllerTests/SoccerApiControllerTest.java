//package de.neuefische.allyourfavorites.controllerTests;
//
//import de.neuefische.allyourfavorites.dto.ApiSoccerTeamList;
//import de.neuefische.allyourfavorites.service.SoccerTeamApiCrawler;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.*;
//
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
////@TestPropertySource(properties = {
////        "x.Auth.Token=4599aaf68eaf463f9499272dda3d7591"
////})
//public class SoccerApiControllerTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @MockBean
//    private SoccerTeamApiCrawler mockedSoccerTeamApiCrawler;
//
//    private final String url = "http://localhost:" + port + "/api/competitions/2002/teams";
//
//    private final String xAuthToken = "4599aaf68eaf463f9499272dda3d7591";
//
//    private HttpHeaders createHttpHeaders(String xAuthToken) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("x-auth-token", xAuthToken);
//        return headers;
//    }
//
//    @Test
//    public void getSoccerTeamsByCompetitionIdTest() {
//        // GIVEN
//
//
//        // WHEN
//        HttpEntity<String> entity = new HttpEntity<>("parameters", createHttpHeaders(xAuthToken));
//        ResponseEntity<ApiSoccerTeamList> response = restTemplate.exchange(url, HttpMethod.GET, entity, ApiSoccerTeamList.class);
//
//        // THEN
//        assertThat(response.getStatusCode(), is(HttpStatus.OK));
//        ApiSoccerTeamList soccerTeamList = new ApiSoccerTeamList();
//
//
//
//
//    }
//}
