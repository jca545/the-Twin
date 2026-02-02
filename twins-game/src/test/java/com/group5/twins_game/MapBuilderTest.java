package com.group5.twins_game;

import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class MapBuilderTest {
    int screenCol = 32;
    int screenRow = 24;
    int screenHeight = screenRow*24;
    int screenWidth = screenCol*24;
    private final BufferedImage backgroundLayer = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);

    
    private final GameHandler gameHandler = new GameHandler();
    private final MapBuilder mapBuilder = new MapBuilder(gameHandler);
    private final MapFactory mapFactory = new MapFactory();
    private final ItemFactory itemFactory = new ItemFactory();
    private final Board board = new Board(screenCol, screenRow);
    private final Graphics2D background2d = backgroundLayer.createGraphics();
    private final KeyControl keyControl = new KeyControl(gameHandler, background2d);
    private final Player player = new Player(keyControl, "red", background2d);

    @Test
    public void testGetKeyTotal() {
        int expectedKeyTotal = 5;
        assertEquals(expectedKeyTotal, mapBuilder.getKeyTotal());
    }

    @Test
    public void testBuildMap() {
        try {
            gameHandler.gameState = gameHandler.playState;
            mapBuilder.buildMap(mapFactory, itemFactory, board, background2d, player);
        } catch (IOException e) {
            fail("IOException was thrown: build map");
        }
    }

    @Test
    public void testBackgroundSetting(){

        try {
            gameHandler.gameState = gameHandler.playState;
            mapBuilder.buildMap(mapFactory, itemFactory, board, background2d, player);
        } catch (IOException e) {
            fail("IOException was thrown: build map");
        }

        for (int x = 0; x <= gameHandler.getMaxIndex("x"); x++) {
            for (int y = 0; y <= gameHandler.getMaxIndex("y"); y++) {
                Cell cell = board.cellBoard[y][x];

                // Check if walls are set on the edge of the screen
                if (x == 0 || x == gameHandler.getMaxIndex("x")
                        || y == 0 || y == gameHandler.getMaxIndex("y")) {
                    assertTrue(cell instanceof WallCell);
                }
            }
        }
    }





    @Test
    public void testGoalCellSet() {

        try {
            gameHandler.gameState = gameHandler.playState;
            mapBuilder.buildMap(mapFactory, itemFactory, board, background2d, player);
        } catch (IOException e) {
            fail("IOException was thrown: build map");
        }

        Cell testCell = board.cellBoard[1][3];
        assertTrue(testCell instanceof GoalCell);
    }

    @Test
    public void testDoorCellSet() {

        try {
            gameHandler.gameState = gameHandler.playState;
            mapBuilder.buildMap(mapFactory, itemFactory, board, background2d, player);
        } catch (IOException e) {
            fail("IOException was thrown: build map");
        }

        Cell testCell = board.cellBoard[2][1];
        assertTrue(testCell instanceof DoorCell);
    }

    @Test
    public void testWallCellsSetting() {
        try {
            gameHandler.gameState = gameHandler.playState;
            mapBuilder.buildMap(mapFactory, itemFactory, board, background2d, player);
        } catch (IOException e) {
            fail("IOException was thrown: build map");
        }

        // Check if wall cells are set correctly
        int[][] wallPositions = {{2, 2}, {3, 2}, {4, 2}, {4, 1}, {16, 12}, {15, 12}, {17, 12}, {16, 11}, {16, 13}};
        for (int[] pos : wallPositions) {
            Cell testCell = board.cellBoard[pos[1]][pos[0]];
            assertTrue(testCell instanceof WallCell);
        }
    }

    @Test
    public void testLeftSideSetting() {
        try {
            gameHandler.gameState = gameHandler.playState;
            mapBuilder.buildMap(mapFactory, itemFactory, board, background2d, player);
        } catch (IOException e) {
            fail("IOException was thrown: build map");
        }

        // Check if wall cells are set correctly
        int[][] wallPositions = {
                {5, 5}, {6, 5}, // Left-top corner
                {7, 4}, {7, 5}, // Left-top corner
                {1, 8}, {2, 8}, {3, 8}, {4, 8}, {5, 8}, {6, 8}, {7, 8}, {8, 8}, {9, 8}, // Left-Middle
                {1, 12}, {2, 12}, // Left-Bottom corner
                {3, 15}, {3, 16}, {3, 17}, {3, 18}, {3, 19}, {3, 20} // Left-Bottom corner
        };
        for (int[] pos : wallPositions) {
            Cell testCell = board.cellBoard[pos[1]][pos[0]];
            assertTrue(testCell instanceof WallCell);
        }
    }

    @Test
    public void testMiddleSetting() {
        try {
            gameHandler.gameState = gameHandler.playState;
            mapBuilder.buildMap(mapFactory, itemFactory, board, background2d, player);
        } catch (IOException e) {
            fail("IOException was thrown: build map");
        }
        Cell testCell;
        // Check if wall cells are set correctly
        for (int x = 11; x <= 18; x++) {
            testCell = board.cellBoard[2][x];
            assertTrue(testCell instanceof WallCell);
        }
        // Middle-Top-Left |
        for (int y = 5; y <= 10; y++) {
            testCell = board.cellBoard[y][12];
            assertTrue(testCell instanceof WallCell);
        }
        // Middle-Top-Right L
        for (int x = 18; x <= 21; x++) {
            testCell = board.cellBoard[7][x];
            assertTrue(testCell instanceof WallCell);
        }
        for (int y = 5; y < 7; y++) {
            testCell = board.cellBoard[y][18];
            assertTrue(testCell instanceof WallCell);
        }
        // Middle-Left L
        for (int x = 8; x < 11; x++) {
            testCell = board.cellBoard[15][x];
            assertTrue(testCell instanceof WallCell);
        }
        for (int y = 11; y < 15; y++) {
            testCell = board.cellBoard[y][8];
            assertTrue(testCell instanceof WallCell);
        }
        // Middle-right |
        for (int y = 10; y <= 14; y++) {
            testCell = board.cellBoard[y][22];
            assertTrue(testCell instanceof WallCell);
        }
        // Middle-Left-Bottom |
        for (int y = 17; y < gameHandler.getMaxIndex("y"); y++) {
            testCell = board.cellBoard[y][6];
            assertTrue(testCell instanceof WallCell);
        }
        // Middle-Left-Bottom -|
        for (int x = 9; x <= 14; x++) {
            testCell = board.cellBoard[19][x];
            assertTrue(testCell instanceof WallCell);
        }
        for (int y = 20; y < (gameHandler.getMaxIndex("y") - 2); y++) {
            testCell = board.cellBoard[y][14];
            assertTrue(testCell instanceof WallCell);
        }
        // Middle-Bottom -
        for (int x = 16; x <= 20; x++) {
            testCell = board.cellBoard[17][x];
            assertTrue(testCell instanceof WallCell);
        }
        // Middle-Right-Bottom _|
        for (int x = 19; x <= 24; x++) {
            testCell = board.cellBoard[21][x];
            assertTrue(testCell instanceof WallCell);
        }
        for (int y = 18; y < 21; y++) {
            testCell = board.cellBoard[y][24];
            assertTrue(testCell instanceof WallCell);
        }
    }

    @Test
    public void testRightSetting() {
        try {
            gameHandler.gameState = gameHandler.playState;
            mapBuilder.buildMap(mapFactory, itemFactory, board, background2d, player);
        } catch (IOException e) {
            fail("IOException was thrown: build map");
        }
        Cell testCell;
        // Right's
        // Right-Top corner -
        for (int x = 24; x <= (gameHandler.getMaxIndex("x") - 2); x++) {
            testCell = board.cellBoard[3][x];
            assertTrue(testCell instanceof WallCell);
        }
        // Right-Middle -
        for (int x = 29; x <= gameHandler.getMaxIndex("x"); x++) {
            testCell = board.cellBoard[6][x];
            assertTrue(testCell instanceof WallCell);
        }
        // Right-Middle L
        for (int x = 26; x <= 28; x++) {
            testCell = board.cellBoard[12][x];
            assertTrue(testCell instanceof WallCell);
        }
        for (int y = 8; y <= 12; y++) {
            testCell = board.cellBoard[y][26];
            assertTrue(testCell instanceof WallCell);
        }
        // Right-Bottom corner -
        for (int x = 25; x <= 27; x++) {
            testCell = board.cellBoard[15][x];
            assertTrue(testCell instanceof WallCell);
        }
        // Right-Bottom corner |
        for (int y = 16; y <= gameHandler.getMaxIndex("y"); y++) {
            if (y != 20 && y != 21) {
                testCell = board.cellBoard[y][27];
                assertTrue(testCell instanceof WallCell);
            }
        }
    }

    @Test
    public void checkAllGrassWhenTitleState() {
        try {
            gameHandler.gameState = gameHandler.titleState;
            mapBuilder.buildMap(mapFactory, itemFactory, board, background2d, player);
        } catch (IOException e) {
            fail("IOException was thrown: build map");
        }

        for (int x = 0; x <= gameHandler.getMaxIndex("x"); x++) {
            for (int y = 0; y <= gameHandler.getMaxIndex("y"); y++) {
                Cell cell = board.cellBoard[y][x];
                assertTrue(cell instanceof GrassCell);
            }
        }
    }

    @Test
    public void testGroundItemsSetting() {
        try {
            gameHandler.gameState = gameHandler.playState;
            mapBuilder.buildMap(mapFactory, itemFactory, board, background2d, player);
        } catch (IOException e) {
            fail("IOException was thrown: build map");
        }

        int keyCount = 0;
        int diamondCount = 0;
        int coinCount = 0;
        int trapCount = 0;
        for (int x = 0; x <= gameHandler.getMaxIndex("x"); x++) {
            for (int y = 0; y <= gameHandler.getMaxIndex("y"); y++) {
                Cell cell = board.cellBoard[y][x];
                for(int i = 0; i < 3; i++) {
                    if (cell.holding[i] instanceof Key) {
                        keyCount++;
                    } else if (cell.holding[i] instanceof Diamond) {
                        diamondCount++;
                    } else if (cell.holding[i] instanceof Coin) {
                        coinCount++;
                    } else if (cell.holding[i] instanceof Trap) {
                        trapCount++;
                    }
                }
            }
        }

        assertEquals(5, keyCount);
        assertEquals(1, diamondCount);
        assertEquals(20, coinCount);
        assertEquals(10, trapCount);
    }

    @Test
    public void testSet3Enemies() {
        Positions[] positions = new Positions[]{new Positions(2, 3), new Positions(4, 5), new Positions(5, 6) };

        try {
            EnemyCollection enemies = mapBuilder.setEnemies(positions, 3, board);

            assertEquals(new Positions(2,3).getxPosition(), enemies.enemyArr[0].position.getxPosition());
            assertEquals(new Positions(2,3).getyPosition(), enemies.enemyArr[0].position.getyPosition());

            assertEquals(new Positions(4,5).getxPosition(), enemies.enemyArr[1].position.getxPosition());
            assertEquals(new Positions(4,5).getyPosition(), enemies.enemyArr[1].position.getyPosition());

            assertEquals(new Positions(5,6).getxPosition(), enemies.enemyArr[2].position.getxPosition());
            assertEquals(new Positions(5,6).getyPosition(), enemies.enemyArr[2].position.getyPosition());
        } catch (IOException e) {
            fail("IOException was thrown: set enemies");
        }

    }
    @Test
    public void testSetNumOfEnemies() {
        Positions[] positions = new Positions[]{new Positions(2, 3), new Positions(4, 5), new Positions(5, 6) };

        try {
            EnemyCollection enemies = mapBuilder.setEnemies(positions, 3, board);
            assertEquals(3, enemies.getHolding());
        } catch (IOException e) {
            fail("IOException was thrown: set enemies");
        }

    }
}
