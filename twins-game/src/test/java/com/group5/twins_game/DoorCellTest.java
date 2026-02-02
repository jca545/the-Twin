package com.group5.twins_game;

import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class DoorCellTest {
    @Test
    public void testConstructor(){
        DoorCell testDoor = new DoorCell(new Positions(0, 0));
        assert(testDoor.solid);
        assertNotNull(testDoor.imgDoorOpened);
        assertNotNull(testDoor.img);
        assertNotNull(testDoor.imgGrass);
    }

    @Test
    public void testUnlockDoor(){
        DoorCell testDoor = new DoorCell(new Positions(0, 0));
        testDoor.unlockDoor();
        assert(!testDoor.solid);
    }

    @Test
    public void testInsert(){
        DoorCell testDoor = new DoorCell(new Positions(0, 0));
        try {
            testDoor.insert(new Coin(new Positions(0,0)));
        } catch (Exception ignore) {
            assert(false);
        }
        for (int i=0; i<testDoor.holding.length; i++) {
            assert(testDoor.holding[i] instanceof NullEntity);
        }
    }

    @Test
    public void testDraw() {
        JFrame gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setTitle("The Twins");
        GameHandler testGameHandler = new GameHandler();
        testGameHandler.gameState = 1;
        testGameHandler.resetGame();
        testGameHandler.createGameLoop();

        gameWindow.add(testGameHandler);
        gameWindow.pack();
        gameWindow.setVisible(true);

        testGameHandler.player.score.addKeysCollected(5);
        testGameHandler.cellBoard.door.unlockDoor();
        assert(!testGameHandler.cellBoard.door.isSolid());
        testGameHandler.cellBoard.door.draw(testGameHandler.background2d);

    }

    @Test
    public void testHasItem() {
        DoorCell testDoor = new DoorCell(new Positions(0, 0));
        assert(!testDoor.hasItem());
    }
}
