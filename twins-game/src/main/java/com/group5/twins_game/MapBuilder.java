package com.group5.twins_game;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

/**
 * The class that designs how the map of this game looks
 *
 * @author Jin Yang
 * @author Jordan Clough
 * @author Joseph Zhang
 * @author Winnie Chan
 *
 * @version 1.0
 * @see Board
 * @see GameHandler
 * @see ItemFactory
 * @see MapFactory
 */
public class MapBuilder {
    GameHandler graph;
    // maxXY = max index of col and row
    // Note: our max x, y = 31, 23
    int maxX, maxY, keyTotal = 5;

    /**
     * Constructor of this map builder class
     * that sets all the cells and items on map
     *
     * @param g the GameHandler class of this game
     *
     * @author Winnie Chan
     */
    public MapBuilder(GameHandler g){
        this.graph = g;
        this.maxX = g.getMaxIndex("x");
        this.maxY = g.getMaxIndex(("y"));
    }

    /**
     * Gets the total amount of keys created in this map
     *
     * @return initial amount of keys on the map
     *
     * @author Winnie Chan
     */
    public int getKeyTotal() {
        return keyTotal;
    }

    /**
     * Function that is creating the map
     *
     * @param mfactory the MapFactory class that creates cells
     * @param ifactory the ItemFactory that creates the GroundItems
     * @param cellBoard the map board of this game
     * @param background used to draw out onto the game window
     * @param player the Player class for this game
     * @throws IOException
     *
     * @author Jordan Clough
     * @suthor Winnie Chan
     */
    public void buildMap(MapFactory mfactory, ItemFactory ifactory, Board cellBoard, Graphics2D background, Player player) throws IOException {
        Positions pos;

        if (graph.gameState != graph.titleState) {
            int a, b;   // for randomizing position's x, y

            // For setting backgrounds
            for (int x = 0; x <= this.maxX; x++) {
                for (int y = 0; y <= this.maxY; y++) {
                    pos = new Positions(x, y);

                    // Setting walls on the edge of screen
                    if (x == 0 || x == this.maxX ||
                            y == 0 || y == this.maxY) {
                        mfactory.makeWallCell(pos, cellBoard, background);
                        // set grasses in the area surrounded by walls
                    } else {
                        mfactory.makeGrassCell(pos, cellBoard, background);
                    }
                }
            }

            //Goal Room
            // Goal cell
            pos = new Positions(3, 1);
            cellBoard.gCell = mfactory.makeGoalCell(pos, cellBoard, background, player);
            // Door
            pos = new Positions(1, 2);
            cellBoard.door = mfactory.makeDoorCell(pos, cellBoard, background);
            // Walls
            pos = new Positions(2, 2);
            mfactory.makeWallCell(pos, cellBoard, background);

            pos = new Positions(3, 2);
            mfactory.makeWallCell(pos, cellBoard, background);

            pos = new Positions(4, 2);
            mfactory.makeWallCell(pos, cellBoard, background);

            pos = new Positions(4, 1);
            mfactory.makeWallCell(pos, cellBoard, background);

            // walls all over the map
            // for the "+" walls in center of map
            pos = new Positions(16, 12);
            mfactory.makeWallCell(pos, cellBoard, background);

            pos = new Positions(15, 12);
            mfactory.makeWallCell(pos, cellBoard, background);

            pos = new Positions(17, 12);
            mfactory.makeWallCell(pos, cellBoard, background);

            pos = new Positions(16, 11);
            mfactory.makeWallCell(pos, cellBoard, background);

            pos = new Positions(16, 13);
            mfactory.makeWallCell(pos, cellBoard, background);

            // Left Side's
            // Left-top corner _|
            for (int x = 5; x < 7; x++) {
                pos = new Positions(x, 5);
                mfactory.makeWallCell(pos, cellBoard, background);
            }
            for (int y = 4; y <= 5; y++) {
                pos = new Positions(7, y);
                mfactory.makeWallCell(pos, cellBoard, background);
            }
            // Left-Middle -
            for (int x = 1; x <= 9; x++) {
                pos = new Positions(x, 8);
                mfactory.makeWallCell(pos, cellBoard, background);
            }
            // Left-Bottom corner -
            for (int x = 1; x <= 2; x++) {
                pos = new Positions(x, 12);
                mfactory.makeWallCell(pos, cellBoard, background);
            }
            // Left-Bottom corner |
            for (int y = 15; y <= 20; y++) {
                pos = new Positions(3, y);
                mfactory.makeWallCell(pos, cellBoard, background);
            }

            // Middle's
            // Middle Top -
            for (int x = 11; x <= 18; x++) {
                pos = new Positions(x, 2);
                mfactory.makeWallCell(pos, cellBoard, background);
            }
            // Middle-Top-Left |
            for (int y = 5; y <= 10; y++) {
                pos = new Positions(12, y);
                mfactory.makeWallCell(pos, cellBoard, background);
            }
            // Middle-Top-Right L
            for (int x = 18; x <= 21; x++) {
                pos = new Positions(x, 7);
                mfactory.makeWallCell(pos, cellBoard, background);
            }
            for (int y = 5; y < 7; y++) {
                pos = new Positions(18, y);
                mfactory.makeWallCell(pos, cellBoard, background);
            }
            // Middle-Left L
            for (int x = 8; x < 11; x++) {
                pos = new Positions(x, 15);
                mfactory.makeWallCell(pos, cellBoard, background);
            }
            for (int y = 11; y < 15; y++) {
                pos = new Positions(8, y);
                mfactory.makeWallCell(pos, cellBoard, background);
            }
            // Middle-right |
            for (int y = 10; y <= 14; y++) {
                pos = new Positions(22, y);
                mfactory.makeWallCell(pos, cellBoard, background);
            }
            // Middle-Left-Bottom |
            for (int y = 17; y < maxY; y++) {
                pos = new Positions(6, y);
                mfactory.makeWallCell(pos, cellBoard, background);
            }
            // Middle-Left-Bottom -|
            for (int x = 9; x <= 14; x++) {
                pos = new Positions(x, 19);
                mfactory.makeWallCell(pos, cellBoard, background);
            }
            for (int y = 20; y < (maxY - 2); y++) {
                pos = new Positions(14, y);
                mfactory.makeWallCell(pos, cellBoard, background);
            }
            // Middle-Bottom -
            for (int x = 16; x <= 20; x++) {
                pos = new Positions(x, 17);
                mfactory.makeWallCell(pos, cellBoard, background);
            }
            // Middle-Right-Bottom _|
            for (int x = 19; x <= 24; x++) {
                pos = new Positions(x, 21);
                mfactory.makeWallCell(pos, cellBoard, background);
            }
            for (int y = 18; y < 21; y++) {
                pos = new Positions(24, y);
                mfactory.makeWallCell(pos, cellBoard, background);
            }

            // Right's
            // Right-Top corner -
            for (int x = 24; x <= (maxX - 2); x++) {
                pos = new Positions(x, 3);
                mfactory.makeWallCell(pos, cellBoard, background);
            }
            // Right-Middle -
            for (int x = 29; x <= maxX; x++) {
                pos = new Positions(x, 6);
                mfactory.makeWallCell(pos, cellBoard, background);
            }
            // Right-Middle L
            for (int x = 26; x <= 28; x++) {
                pos = new Positions(x, 12);
                mfactory.makeWallCell(pos, cellBoard, background);
            }
            for (int y = 8; y <= 12; y++) {
                pos = new Positions(26, y);
                mfactory.makeWallCell(pos, cellBoard, background);
            }
            // Right-Bottom corner -
            for (int x = 25; x <= 27; x++) {
                pos = new Positions(x, 15);
                mfactory.makeWallCell(pos, cellBoard, background);
            }
            // Right-Bottom corner |
            for (int y = 16; y <= maxY; y++) {
                if (y != 20 && y != 21) {
                    pos = new Positions(27, y);
                    mfactory.makeWallCell(pos, cellBoard, background);
                }
            }

            // Random position for GroundItems
            Random rand = new Random();
            keyTotal = 0;
            for (int itemAmount = 0; itemAmount < 36; itemAmount++) {
                a = rand.nextInt(maxX - 2) + 1;
                b = rand.nextInt(maxY - 2) + 1;
                pos = new Positions(a, b);

                while (!cellBoard.availableToPut(pos)) {
                    a = rand.nextInt(maxX - 2) + 1;
                    b = rand.nextInt(maxY - 2) + 1;
                    pos = new Positions(a, b);
                }
                // 5 keys
                if (itemAmount < 5) {
                    ifactory.makeKey(pos, cellBoard, background);
                    keyTotal++;
                    // 1 diamond    + 20 each
                } else if (itemAmount < 6) {
                    ifactory.makeDiamond(pos, cellBoard, background);
                    // 10 traps     - 10 each
                } else if (itemAmount < 16) {
                    ifactory.makeTrap(pos, cellBoard, background);
                    // 20 coins     + 5 each
                } else {
                    ifactory.makeCoin(pos, cellBoard, background);
                }
            }
        }
    }

    /**
     * Creates an array containing all the enemies for this game
     *
     * @param pos array of Position class that stores
     *            the initial position of enemies.
     * @param numEnemies amount of enemies needed for this game
     * @param cellBd the game board for this game
     * @return the array contain all created enemies
     * @throws IOException
     *
     * @author Jin Yang
     * @author Jordan Clough
     * @author Winnie Chan
     */
    public EnemyCollection setEnemies(Positions[] pos, int numEnemies, Board cellBd) throws IOException {
        EnemyCollection ret = new EnemyCollection();
        for (int i = 0; i < numEnemies; i++) {
            ret.makeEnemy(pos[i],cellBd, i);
        }
        return ret;
    }
}
