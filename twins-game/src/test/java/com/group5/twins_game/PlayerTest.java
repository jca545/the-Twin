package com.group5.twins_game;

import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.Assert.*;

public class PlayerTest {

    int screenCol = 32;
    int screenRow = 24;
    int screenHeight = screenRow*24;
    int screenWidth = screenCol*24;
    BufferedImage backgroundLayer = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D background2d = backgroundLayer.createGraphics();
    GameHandler gh = new GameHandler();

    Board board = new Board(gh.screenCol, gh.screenRow);
    KeyControl key = new KeyControl(gh, background2d);
    @Test
    public void testMove()
    {
        Player testing = new Player(key,"red",background2d);

        //going left
        key.leftPressed = true;
        testing.update(board);
        assertTrue(testing.position.getDoublex() == 20-testing.getsteps());
        assertTrue(testing.position.getDoubley() == 10);
        key.leftPressed = false;

        //going right
        key.rightPressed = true;
        testing.update(board);
        assertTrue(testing.position.getDoublex() == 20);
        assertTrue(testing.position.getDoubley() == 10);
        key.rightPressed = false;

        //going up
        key.upPressed = true;
        testing.update(board);
        assertTrue(testing.position.getDoublex() == 20);
        assertTrue(testing.position.getDoubley() == 10-testing.getsteps());
        key.upPressed = false;

        //going down
        key.downPressed = true;
        testing.update(board);
        assertTrue(testing.position.getDoublex() == 20);
        assertTrue(testing.position.getDoubley() == 10);
        key.downPressed = false;
    }

    @Test
    public void testCellMove()
    {
        Player testing = new Player(key,"red",background2d);

        testing.position.setPositions(20.45,10.45);

        //move left once, inserting self onto cell
        testing.keys.leftPressed = true;
        testing.update(board);
        testing.keys.leftPressed = false;

        assertEquals(board.cellBoard[10][20].holding[1].getEntityID(), "Player");

        //move left once more, not changing cell
        testing.keys.leftPressed = true;
        testing.update(board);
        testing.keys.leftPressed = false;

        assertEquals(board.cellBoard[10][20].holding[1].getEntityID(), "Player");

        //move right thrice, changing cell to one to the right
        testing.keys.rightPressed = true;
        testing.update(board);
        testing.update(board);
        testing.update(board);
        testing.keys.rightPressed = false;

        assertEquals(board.cellBoard[10][21].holding[1].getEntityID(), "Player");

        //move up down once, chaning cell to one down
        testing.keys.downPressed = true;
        testing.update(board);
        testing.keys.downPressed = false;

        assertEquals(board.cellBoard[11][21].holding[1].getEntityID(), "Player");
    }

    @Test
    public void testBlockedMove()
    {
        Player testing = new Player(key,"red",background2d);

        //instantiating player, inserting into board
        init(testing,new Positions(20,10));
        //going right
        board.cellBoard[10][21] = new WallCell(new Positions(21,10));
        key.rightPressed = true;
        for (int i = 0; i<10; i++) {
            testing.update(board);
        }
        key.rightPressed = false;
        assertEquals(board.cellBoard[10][20].holding[1].getEntityID(), "Player");
        board.cellBoard[10][21] = new GrassCell(new Positions(21,10));



        board.cellBoard[10][19] = new WallCell(new Positions(19,10));
        key.leftPressed = true;
        for (int i = 0; i<10; i++) {
            testing.update(board);
        }
        key.leftPressed = false;
        assertEquals(board.cellBoard[10][20].holding[1].getEntityID(), "Player");
        board.cellBoard[10][19] = new GrassCell(new Positions(19,10));

        board.cellBoard[11][20] = new WallCell(new Positions(20,11));
        key.downPressed = true;
        for (int i = 0; i<10; i++) {
            testing.update(board);
        }
        key.downPressed = false;
        assertEquals(board.cellBoard[10][20].holding[1].getEntityID(), "Player");
        board.cellBoard[11][20] = new GrassCell(new Positions(11,20));


        board.cellBoard[9][20] = new WallCell(new Positions(20,9));
        key.upPressed = true;
        for (int i = 0; i<10; i++) {
            testing.update(board);
        }
        key.upPressed = false;
        assertEquals(board.cellBoard[10][20].holding[1].getEntityID(), "Player");
        board.cellBoard[9][20] = new GrassCell(new Positions(9,20));
    }

    @Test
    public void testMoveThroughDoor()
    {
        Player testing = new Player(key,"red",background2d);

        //instantiating player, inserting into board
        init(testing,new Positions(20.45,10));


        //going right, trying to get through a blocked door
        board.cellBoard[10][21] = new DoorCell(new Positions(21,10));
        key.rightPressed = true;
        for (int i = 0; i<10; i++) {
            testing.update(board);
        }
        key.rightPressed = false;
        assertEquals(board.cellBoard[10][20].holding[1].getEntityID(), "Player");
        board.cellBoard[10][21] = new GrassCell(new Positions(21,10));

        //going right, opening the door
        board.cellBoard[10][21].solid = false;
        key.rightPressed = true;
            testing.update(board);
        key.rightPressed = false;
        assertEquals(board.cellBoard[10][21].holding[1].getEntityID(), "Player");
        board.cellBoard[10][21] = new GrassCell(new Positions(21,10));
    }



    @Test
    public void testGetItem() throws IOException {
        Player testing = new Player(key,"red",background2d);

        //instantiating player, inserting into board
        init(testing,new Positions(20.45,10));

        //setting coin
        board.cellBoard[10][21].insert(new Coin(new Positions(21,10)));
        //checking if ground item has been set
        assertEquals(board.cellBoard[10][21].holding[0].entityID, "Coin");

        //crossing the cell into the cell of the coin
        testing.keys.rightPressed = true;
        testing.update(board);
        testing.keys.rightPressed = false;
        //checking if ground item has been removed
        assertEquals(board.cellBoard[10][21].holding[0].entityID, "NullEntity");
        //checking if score has been added
        assertTrue(testing.score.getPoints() == 15 );

        board.cellBoard[10][20].insert(new Key(new Positions(20,10)));
        assertEquals(board.cellBoard[10][20].holding[0].entityID, "Key");
        //crossing the cell into the cell of the coin
        testing.keys.leftPressed = true;
        testing.update(board);
        testing.keys.leftPressed = false;
        //checking if ground item has been removed
        assertEquals(board.cellBoard[10][20].holding[0].entityID, "NullEntity");
        //checking if score has been added
        assertTrue(testing.score.getKeysCollected() == 1 );
    }

    @Test
    public void TestAtGoal()
    {
        Player testing = new Player(key,"red",background2d);
        init(testing,new Positions(20,10));
        assertTrue(testing.isAtGoal(new GoalCell(new Positions(20,10),testing)));
        testing.position.setPositions(10,10);
        assertFalse(testing.isAtGoal(new GoalCell(new Positions(20,10),testing)));
        testing.position.setPositions(19,10);
        assertFalse(testing.isAtGoal(new GoalCell(new Positions(20,10),testing)));
        testing.position.setPositions(20,9);
        assertFalse(testing.isAtGoal(new GoalCell(new Positions(20,10),testing)));
    }

    private void init(Player p, Positions pos)
    {
        p.keys.leftPressed = true;
        p.update(board);
        p.keys.leftPressed = false;
        p.position.setxPosition(pos.getDoublex());
        p.position.setyPosition(pos.getDoubley());
    }
}
