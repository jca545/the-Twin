package com.group5.twins_game;

import java.awt.*;
import java.io.IOException;

/**
 * A factory class that create items in given position
 * display this item, and insert this item into the cell
 * with given position
 *
 * @author Joseph Zhang
 * @author Winnie Chan
 *
 * @version 1.0
 * @see GameHandler
 * @see MapBuilder
 * @see Entity
 * @see GroundItem
 * @see Coin
 * @see Diamond
 * @see Key
 * @see Trap
 */
public class ItemFactory {
    /**
     * create a coin
     * @param pos the position to set this coin
     * @param cellBoard the game board
     * @param background used ot draw out onto the game window
     *
     * @author Winnie Chan
     */
    public void makeCoin(Positions pos, Board cellBoard, Graphics2D background) {
        try {
            Coin coin;
            coin = new Coin(pos);
            cellBoard.insert(coin,pos);
            coin.draw(background);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * create a diamond
     * @param pos the position to set this diamond
     * @param cellBoard the game board
     * @param background used ot draw out onto the game window
     *
     * @author Winnie Chan
     */
    public void makeDiamond(Positions pos, Board cellBoard, Graphics2D background) {
        try {
            Diamond diamond;
            diamond = new Diamond(pos);
            cellBoard.insert(diamond,pos);
            diamond.draw(background);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * create a trap
     * @param pos the position to set this trap
     * @param cellBoard the game board
     * @param background used ot draw out onto the game window
     *
     * @author Winnie Chan
     */
    public void makeTrap(Positions pos, Board cellBoard, Graphics2D background) {
        try {
            Trap trap;
            trap = new Trap(pos);
            cellBoard.insert(trap,pos);
            trap.draw(background);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * create a key
     * @param pos the position to set this key
     * @param cellBoard the game board
     * @param background used ot draw out onto the game window
     *
     * @author Winnie Chan
     */
    public void makeKey(Positions pos, Board cellBoard, Graphics2D background) {
        try {
            Key key;
            key = new Key(pos);
            cellBoard.insert(key,pos);
            key.draw(background);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
