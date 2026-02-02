package com.group5.twins_game;

import org.junit.Test;
import java.awt.*;

import static org.junit.Assert.*;

public class BoardTest {
    GameHandler gh = new GameHandler();
    Board board = new Board(gh.screenCol, gh.screenRow);
    Graphics2D background = gh.background2d;
    KeyControl keys = new KeyControl(gh, background);
    Player player = new Player(keys, gh.which_character, background);
    MapFactory mfactory = new MapFactory();
    ItemFactory ifactory = new ItemFactory();

    @Test
    public void testMove(){
        int newX = player.position.getxPosition();
        int newY = player.position.getyPosition() + 1;
        Positions newPos = new Positions(newX, newY);
        board.move(player, newPos);

        Cell cell = board.cellBoard[newY][newX];
        Entity entity = cell.holding[1];
        assertEquals("Player", entity.entityID);

        cell = board.cellBoard[player.position.getxPosition()][player.position.getyPosition()];
        entity = cell.holding[1];
        assertEquals("NullEntity", entity.entityID);
    }

    @Test
    public void testUnblockedCell(){
        Positions pos = new Positions(10, 10);
        assertTrue(board.unblockedCell(pos));
    }
    @Test
    public void testBLOCKEDCell(){
        Positions pos = new Positions(11, 11);
        mfactory.makeWallCell(pos, board, background);
        assertFalse(board.unblockedCell(pos));
    }

    @Test
    public void testItemNOTInCell(){
        Positions pos = new Positions(12, 12);
        assertFalse(board.itemInCell(pos));
    }
    @Test
    public void testItemInCell(){
        Positions pos = new Positions(13,13);
        ifactory.makeCoin(pos, board, background);
        assertTrue(board.itemInCell(pos));
    }

    @Test
    public void testAvailableToPut_onEmpty(){
        Positions pos = new Positions(14,14);
        assertTrue(board.availableToPut(pos));
    }
    @Test
    public void testAvailableToPut_onWall(){
        Positions pos = new Positions(15, 15);
        mfactory.makeWallCell(pos, board, background);
        assertFalse(board.availableToPut(pos));
    }
    @Test
    public void testAvailableToPut_inGoal(){
        Positions pos = new Positions(1,1);
        assertFalse(board.availableToPut(pos));
    }
}
