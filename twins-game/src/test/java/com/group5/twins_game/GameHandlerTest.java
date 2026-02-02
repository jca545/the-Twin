package com.group5.twins_game;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

import javax.swing.*;

public class GameHandlerTest {
    private GameHandler defaultGameHandler() {
        return new GameHandler();
    }
    @Test
    public void testConstructor() {
        GameHandler testGameHandler = defaultGameHandler();
        assertEquals(3, testGameHandler.gameState);
        assertEquals(1, testGameHandler.ui.commandNum);
    }

    @Test
    public void testGetMaxIndex() {
        GameHandler testGameHandler = defaultGameHandler();
        assertEquals(31, testGameHandler.getMaxIndex("x"));
        assertEquals(23, testGameHandler.getMaxIndex("y"));
        assertEquals(-1, testGameHandler.getMaxIndex("test"));
        assertEquals(-1, testGameHandler.getMaxIndex(""));
    }

    @Test
    public void testCreateGameLoop() {
        GameHandler testGameHandler = defaultGameHandler();
        testGameHandler.createGameLoop();
        assert(testGameHandler.gameLoop.isAlive());
    }

    @Test
    public void testResetGame() {
        GameHandler testGameHandler = defaultGameHandler();
        testGameHandler.resetGame();
        assertEquals(0, testGameHandler.frame);
        assertEquals(0, testGameHandler.clock);
        assertNotNull(testGameHandler.player);
        assertNotNull(testGameHandler.cellBoard);
        assertNotNull(testGameHandler.builder);
        assertNotNull(testGameHandler.mfactory);
        assertNotNull(testGameHandler.ifactory);
        assertNotNull(testGameHandler.player.position);
    }

    @Test
    public void testUpdate() {
        GameHandler testGameHandler = defaultGameHandler();
        testGameHandler.createGameLoop();
        testGameHandler.resetGame();
        // In case there is a runtime exception
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (Exception ignored) {
            assert (false);
        }

        // cellboard is null, gamestate is 1
        testGameHandler = defaultGameHandler();
        testGameHandler.gameState = 1;
        testGameHandler.createGameLoop();
        testGameHandler.resetGame();
        testGameHandler.cellBoard = null;
        try {
            testGameHandler.update();
        } catch (Exception ignored) {
            assert (false);
        }
        assertEquals(1, testGameHandler.gameState);
        assertNotNull(testGameHandler.player);
        assertNull(testGameHandler.cellBoard);

        // player is null, gamestate is 1
        testGameHandler = defaultGameHandler();
        testGameHandler.gameState = 1;
        testGameHandler.createGameLoop();
        testGameHandler.resetGame();
        testGameHandler.player = null;
        try {
            testGameHandler.update();
        } catch (Exception ignored) {
            assert (false);
        }
        assertEquals(1, testGameHandler.gameState);
        assertNull(testGameHandler.player);
        assertNotNull(testGameHandler.cellBoard);

        // Check for initial values
        testGameHandler = defaultGameHandler();
        testGameHandler.gameState = 1;
        testGameHandler.createGameLoop();
        testGameHandler.resetGame();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (Exception ignored) {
            assert (false);
        }
        assert (0 < testGameHandler.frame);

        // Check that frame and clock are being updated correctly
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ignore) {
        }
        assert (0 <= testGameHandler.frame && 60 >= testGameHandler.frame);
        assert (0 < testGameHandler.clock);

        // Check if door opens once we have collected enough keys
        testGameHandler.player.score.addKeysCollected(5);
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (Exception ignored) {
            assert (false);
        }
        assert (!testGameHandler.cellBoard.door.isSolid());

        // If player moves onto goal cell
        testGameHandler.player.position.setPositions(3, 1);
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (Exception ignored) {
            assert (false);
        }
        assertEquals(4, testGameHandler.gameState);
    }
    @Test
    public void testUpdateEnemyCaughtAndScoreBelowZero() {
        GameHandler testGameHandler = new GameHandler();
        testGameHandler.gameState = 1;
        testGameHandler.createGameLoop();
        testGameHandler.resetGame();
        testGameHandler.player.score.addPoints(-20);
        testGameHandler.enemyCol.caught = true;
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (Exception ignore) {
        }
        assertEquals(5, testGameHandler.gameState);
    }

    @Test
    public void testUpdateEnemyNotCaughtAndScoreBelowZero() {
        GameHandler testGameHandler = new GameHandler();
        testGameHandler.gameState = 1;
        testGameHandler.createGameLoop();
        testGameHandler.resetGame();
        testGameHandler.player.score.addPoints(-20);
        testGameHandler.enemyCol.caught = false;
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (Exception ignore) {
        }
        assertEquals(5, testGameHandler.gameState);
    }

    @Test
    public void testUpdateEnemyCaughtAndScoreAboveZero() {
        GameHandler testGameHandler = new GameHandler();
        testGameHandler.gameState = 1;
        testGameHandler.createGameLoop();
        testGameHandler.resetGame();
        testGameHandler.enemyCol.caught = true;
        try {
            testGameHandler.update();
        } catch (Exception ignore) {
        }
        assertEquals(5, testGameHandler.gameState);
    }

    @Test
    public void testUpdateEnemyNotCaughtAndScoreAboveZero() {
        GameHandler testGameHandler = new GameHandler();
        testGameHandler.gameState = 1;
        testGameHandler.createGameLoop();
        testGameHandler.resetGame();
        testGameHandler.enemyCol.caught = false;
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (Exception ignore) {
        }
        assertEquals(1, testGameHandler.gameState);
    }

    @Test
    public void testRun() {
        GameHandler testGameHandler = defaultGameHandler();
        testGameHandler.createGameLoop();
        testGameHandler.resetGame();
        testGameHandler.player = null;
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (Exception ignore) {
        }
        assert(testGameHandler.runningGame);
        assertNull(testGameHandler.player);

        testGameHandler.createGameLoop();
        testGameHandler.resetGame();
        testGameHandler.runningGame = false;
        try {
            testGameHandler.run();
        } catch (Exception ignore) {
        }
        assert(!testGameHandler.runningGame);

    }

    @Test
    public void testPaintComponent() {
        JFrame gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setTitle("The Twins");

        GameHandler testGameHandler = new GameHandler();
        testGameHandler.gameState = 1;
        testGameHandler.createGameLoop();
        testGameHandler.resetGame();

        gameWindow.add(testGameHandler);
        gameWindow.pack();

        gameWindow.setVisible(true);
        for (int i=1; i<8; i++) {
            testGameHandler.gameState = i;
            testGameHandler.createGameLoop();
            testGameHandler.resetGame();

            assertEquals(i, testGameHandler.gameState);
        }

        gameWindow.dispose();
    }
}
