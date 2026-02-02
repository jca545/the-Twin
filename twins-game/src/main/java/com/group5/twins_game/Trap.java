package com.group5.twins_game;

import java.io.IOException;

/**
 * The punishment that makes player's
 * score decrease after stepping on it,
 * set ID to "Trap"
 *
 * @author Winnie Chan
 *
 * @version 1.0
 * @see GroundItem
 * @see Entity
 * @see Player
 * @see ItemFactory
 */
public class Trap extends GroundItem{
    /**
     * Constructor of this Trap class that set the
     * image of the trap and position of it for this game
     *
     * @param pos input a Position class
     *             of where to set this trap
     * @throws IOException Throws when the program can not
     * find the file with given path
     *
     * @author Winnie Chan
     */
    public Trap(Positions pos) throws IOException {
        entityID = "Trap";

        loader = new ImgLoader();
        img = loader.readImg("/misc_entities/trap.png");
        position = pos;
    }

    /**
     * Specified how many score will play lose
     * after collision on a cell containing trap
     *
     * @return the amount of score player loses
     * after collision with a trap
     *
     * @author Winnie Chan
     *
     * @see GroundItem
     */
    public int gains() {
        return -10;
    }
}
