package pages;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TrelloBoard {

    private String apiKey;
    private String apiToken;
    private String baseUrl;

    public TrelloBoard(String apiKey, String apiToken) {
        this.apiKey = apiKey;
        this.apiToken = apiToken;
        this.baseUrl = "https://api.trello.com/1";
        RestAssured.baseURI = baseUrl;
    }

    public Response createBoard(String boardName) {
        return RestAssured.given()
                .queryParam("key", apiKey)
                .queryParam("token", apiToken)
                .queryParam("name", boardName)
                .contentType(ContentType.JSON)
                .when()
                .post("/boards");
    }

    public Response deleteBoard(String boardId) {
        return RestAssured.given()
                .queryParam("key", apiKey)
                .queryParam("token", apiToken)
                .contentType(ContentType.JSON)
                .when()
                .delete("/boards/{id}", boardId);
    }





}
