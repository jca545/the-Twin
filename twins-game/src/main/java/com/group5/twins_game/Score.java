package com.group5.twins_game;

/**
 * A class to keep track of player's score and
 * the amount of keys player collected
 *
 * @author Jordan Clough
 * @author Joseph Zhang
 *
 * @version 1.0
 * @see Player
 */
public class Score {
    private static int points;
    private static int keysCollected;

    /**
     * Constructor of the Score class
     * that sets the initial point and
     * also set the initial keyCollected to 0
     *
     * @author Jordan Clough
     * @author Joseph Zhang
     */
    public Score() {
        points = 10;
        keysCollected = 0;
    }

    /**
     * A public function that allows other class
     * to get the point value
     *
     * @return score gained until now
     *
     * @author Joseph Zhang
     */
    public int getPoints() {
        return points;
    }

    /**
     * A public function that allows other class
     * to ge the number of keys collected
     *
     * @return the amount of keys collected
     *
     * @author Joseph Zhang
     */
    public int getKeysCollected() {
        return keysCollected;
    }

    /**
     * Add the input amount of points into the
     * total score gained
     *
     * @param newPoints the amount of points to add into total
     *
     * @author Joseph Zhang
     */
    public void addPoints(int newPoints) {
        points += newPoints;
    }

    /**
     * Add hte input amount of keys into the
     * total amount of collected keys
     *
     * @param newKeysCollected the amount of keys to add into total
     *
     * @author Joseph Zhang
     */
    public void addKeysCollected(int newKeysCollected) {
        keysCollected += newKeysCollected;
    }
}