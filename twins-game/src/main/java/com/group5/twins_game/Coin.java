package com.group5.twins_game;

import java.io.IOException;

/**
 * The normal regular reward that makes player's
 * score increase after collecting it
 *
 * @author Joseph Zhang
 * @author Winnie Chan
 *
 * @version 1.0
 * @see GroundItem
 * @see Entity
 * @see Player
 * @see ItemFactory
 */
public class Coin extends GroundItem{
    /**
     * Constructor of this Coin class that sets the
     * image of the coins and the position of it for this game,
     * set ID to "Coin"
     *
     * @param pos input a Position class
     *            of where to set this coin
     * @throws IOException Throws when the program can not
     * find the file with given path
     *
     * @author Winnie Chan
     */
    public Coin(Positions pos) throws IOException {
        entityID = "Coin";

        loader = new ImgLoader();
        img = loader.readImg("/misc_entities/coin.png");
        position = pos;
    }

    /**
     * Specified how many score will play receive
     * after collecting a coin.
     *
     * @author Joseph Zhang
     *
     * @return the amount of score player gains
     * after collecting a coin
     *
     * @see GroundItem
     */
    public int gains() {
        return 5;
    }
}
