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

public class TrelloBoardTest {


    private String boardId;
    private TrelloBoard trelloBoard;

    @BeforeClass
    public void setUp() {
        trelloBoard = new TrelloBoard(API_KEY, API_TOKEN);
    }

    @Test(priority = 1)
    public void testCreateBoard() {
        Response createBoardResponse = trelloBoard.createBoard("Test Board");
        Assert.assertEquals(createBoardResponse.getStatusCode(), 200, "Board failed");
        boardId = createBoardResponse.path("id");
    }

    @Test(priority = 2)
    public void testDeleteBoard() {
        Response deleteBoardResponse = trelloBoard.deleteBoard(boardId);
        Assert.assertEquals(deleteBoardResponse.getStatusCode(), 200, "board delete failed");
    }
}
