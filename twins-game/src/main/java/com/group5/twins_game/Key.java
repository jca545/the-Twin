package com.group5.twins_game;

import java.io.IOException;

/**
 * The class for winning condition's item: key
 *
 * @author Winnie Chan
 * @author Joseph Zhang
 * @author Jordan Clough
 *
 * @version 1.0
 * @see GroundItem
 * @see Entity
 * @see Player
 * @see ItemFactory
 */
public class Key extends GroundItem {
    /**
     * Constructor of this Key class that set the
     * image of the key and position of it for this game,
     * set ID to "Key"
     *
     * @param pos input a Position class
     *             of where to set this key
     * @throws IOException Throws when the program can not
     * find the file with given path
     *
     * @author Winnie Chan
     */
    public Key(Positions pos) throws IOException {
        entityID = "Key";

        loader = new ImgLoader();
        img = loader.readImg("/misc_entities/key.png");
        position = pos;
    }

    /**
     * Constructor of this Key class with no Position
     * parameter to simply gets the image of this key.
     *
     * @throws IOException Throws when the program can not
     * find the file with given path
     *
     * @author Joseph Zhang
     * @author Jordan Clough
     */
    public Key() throws IOException{
        loader = new ImgLoader();
        img = loader.readImg("/misc_entities/key.png");
        entityID = "Key";
    }

    /**
     * Specified how many key will play receive
     * after collecting a kay.
     *
     * @return the amount of key player gains
     * after collecting a key
     *
     * @author Joseph Zhang
     *
     * @see GroundItem
     */
    public int gains() {
        return 1;
    }
}
