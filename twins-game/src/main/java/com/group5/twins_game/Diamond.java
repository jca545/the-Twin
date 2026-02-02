package com.group5.twins_game;

import java.io.IOException;

/**
 * The Special bonus reward that makes player's score
 * increase double the amount of normal reward's score,
 * set ID to "Diamond"
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
public class Diamond extends GroundItem {
    /**
     * Constructor of this Diamond class that set the
     * image of the diamond and position of it for this game
     *
     * @param pos input a Position class
     *             of where to set this diamond
     * @throws IOException Throws when the program can not
     * find the file with given path
     *
     * @author Winnie Chan
     */
    public Diamond(Positions pos) throws IOException {
        entityID = "Diamond";

        loader = new ImgLoader();
        img = loader.readImg("/misc_entities/diamond.png");
        position = pos;
    }

    /**
     * Specified how many score will play receive
     * after collecting a diamond.
     *
     * @return the amount of score player gains
     * after collecting a diamond
     *
     * @author Joseph Zhang
     * @author Winnie Chan
     * @see GroundItem
     */
    public int gains() {
        return 20;
    }
}
