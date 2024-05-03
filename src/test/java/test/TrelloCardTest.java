package test;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.TrelloBoard;
import pages.TrelloCard;
import pages.TrelloList;

import java.util.Random;

import static util.Constants.API_KEY;
import static util.Constants.API_TOKEN;

public class TrelloCardTest {


    private String boardId;
    private String cardIdFirst;
    private String cardIdSecond;
    private String createdListId;

    private TrelloBoard trelloBoard;
    private TrelloList trelloList;
    private TrelloCard trelloCard;

    @BeforeClass
    public void setUp() {

        trelloBoard = new TrelloBoard(API_KEY, API_TOKEN);
        trelloList = new TrelloList(API_KEY, API_TOKEN);
        trelloCard = new TrelloCard(API_KEY, API_TOKEN);




    }


    @Test(priority = 1)
    public void testCreateCards() {

        Response createBoardResponse = trelloBoard.createBoard("Test Board");
        Assert.assertEquals(createBoardResponse.getStatusCode(), 200, "Board failed");
        boardId = createBoardResponse.path("id");

        Response createdListResponse = trelloList.createList(boardId,"new list");
        createdListId = createdListResponse.path("id");

        Response createCardResponse1 = trelloCard.createCard(createdListId, "card first");
        Assert.assertEquals(createCardResponse1.getStatusCode(), 200, "card first  failed");
        cardIdFirst = createCardResponse1.path("id");

        Response createCardResponse2 = trelloCard.createCard(createdListId, "card second");
        Assert.assertEquals(createCardResponse2.getStatusCode(), 200, "card second failed");
        cardIdSecond = createCardResponse2.path("id");
    }

    @Test(priority = 2)
    public void testUpdateRandomCard() {
        Random random = new Random();
        boolean updateFirstCard = random.nextBoolean();

        String cardIdUpdate = updateFirstCard ? cardIdFirst : cardIdSecond;

        Response updateCardResponse = trelloCard.updateCard(cardIdUpdate, "update card");
        Assert.assertEquals(updateCardResponse.getStatusCode(), 200, "update failed");
    }

    @Test(priority = 3)
    public void testDeleteCards() {
        Response deleteCardResponseFirst = trelloCard.deleteCard(cardIdFirst);
        Assert.assertEquals(deleteCardResponseFirst.getStatusCode(), 200, "card first delete failed");

        Response deleteCardResponseSecond = trelloCard.deleteCard(cardIdSecond);
        Assert.assertEquals(deleteCardResponseSecond.getStatusCode(), 200, "card second delete failed");
    }

}
