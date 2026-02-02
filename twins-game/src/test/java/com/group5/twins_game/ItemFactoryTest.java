package com.group5.twins_game;

import org.junit.Test;
import java.awt.*;
import static org.junit.Assert.*;

public class ItemFactoryTest {
    GameHandler gh = new GameHandler();
    Board board = new Board(gh.screenCol, gh.screenRow);
    Graphics2D background = gh.background2d;
    ItemFactory factory = new ItemFactory();

    @Test
    public void testMakeCoin(){
        Positions pos = new Positions(5, 5);
        factory.makeCoin(pos, board, background);
        int y = pos.getyPosition();
        int x = pos.getxPosition();
        Cell cell = board.cellBoard[y][x];
        Entity item = cell.holding[0];
        assertEquals("Coin", item.entityID);
    }

    @Test
    public void testMakeDiamond(){
        Positions pos = new Positions(10, 10);
        factory.makeDiamond(pos, board, background);
        int y = pos.getyPosition();
        int x = pos.getxPosition();
        Cell cell = board.cellBoard[y][x];
        Entity item = cell.holding[0];
        assertEquals("Diamond", item.entityID);
    }

    @Test
    public void testMakeTrap(){
        Positions pos = new Positions(15, 15);
        factory.makeTrap(pos, board, background);
        int y = pos.getyPosition();
        int x = pos.getxPosition();
        Cell cell = board.cellBoard[y][x];
        Entity item = cell.holding[0];
        assertEquals("Trap", item.entityID);
    }

    @Test
    public void testMakeKey(){
        Positions pos = new Positions(15, 15);
        factory.makeKey(pos, board, background);
        int y = pos.getyPosition();
        int x = pos.getxPosition();
        Cell cell = board.cellBoard[y][x];
        Entity item = cell.holding[0];
        assertEquals("Key", item.entityID);
    }

}
