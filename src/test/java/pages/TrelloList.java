package pages;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TrelloList {

    private String apiKey;
    private String apiToken;
    private String baseUrl;

    public TrelloList(String apiKey, String apiToken) {
        this.apiKey = apiKey;
        this.apiToken = apiToken;
        this.baseUrl = "https://api.trello.com/1";
        RestAssured.baseURI = baseUrl;
    }

    public Response createList(String boardId, String listName) {
        Response response = RestAssured.given()
                .queryParam("key", apiKey)
                .queryParam("token", apiToken)
                .queryParam("idBoard", boardId)
                .queryParam("name", listName)
                .contentType(ContentType.JSON)
                .when()
                .post("/lists");

        return response;
    }


}
