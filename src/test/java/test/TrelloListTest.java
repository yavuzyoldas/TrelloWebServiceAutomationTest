package test;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.TrelloBoard;
import pages.TrelloList;

import static util.Constants.API_KEY;
import static util.Constants.API_TOKEN;

public class TrelloListTest {

    private String boardId;
    private TrelloList trelloList;
    private TrelloBoard trelloBoard;

    @BeforeClass
    public void setUp() {
        trelloBoard = new TrelloBoard(API_KEY, API_TOKEN);
        trelloList = new TrelloList(API_KEY, API_TOKEN);
    }

    @Test(priority = 1)
    public void testCreateList() {

        Response createBoardResponse = trelloBoard.createBoard("test board for list");
        boardId = createBoardResponse.path("id");

        Response createListResponse = trelloList.createList(boardId,"new list");
        Assert.assertEquals(createListResponse.getStatusCode(), 200, "List failed");

    }



}
