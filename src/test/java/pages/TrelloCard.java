package pages;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TrelloCard {

    private String apiKey;
    private String apiToken;
    private String baseUrl;

    public TrelloCard(String apiKey, String apiToken) {
        this.apiKey = apiKey;
        this.apiToken = apiToken;
        this.baseUrl = "https://api.trello.com/1";
        RestAssured.baseURI = baseUrl;
    }


    public Response createCard(String boardId, String cardName) {
        return RestAssured.given()
                .queryParam("key", apiKey)
                .queryParam("token", apiToken)
                .queryParam("idList", boardId)
                .queryParam("name", cardName)
                .contentType(ContentType.JSON)
                .when()
                .post("/cards");
    }


    public Response updateCard(String cardId, String updatedCardName) {
        return RestAssured.given()
                .queryParam("key", apiKey)
                .queryParam("token", apiToken)
                .queryParam("name", updatedCardName)
                .contentType(ContentType.JSON)
                .when()
                .put("/cards/{id}", cardId);
    }

    public Response deleteCard(String cardId) {
        return RestAssured.given()
                .queryParam("key", apiKey)
                .queryParam("token", apiToken)
                .contentType(ContentType.JSON)
                .when()
                .delete("/cards/{id}", cardId);
    }
}
