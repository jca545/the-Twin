package com.group5.twins_game;

/**
 * An Entity class that initially filled the
 * cell's containing array to represent that no
 * item, player, or enemy are on this cell
 *
 * @author Jin Yang
 *
 * @version 1.0
 * @see Entity
 * @see Cell
 * @see GrassCell
 */
public class NullEntity extends Entity {
    /**
     * Constructor of NullEntity class that
     * sets ID to "NullEntity"
     *
     * @author Jin Yang
     */
    public NullEntity() {
        entityID = "NullEntity";
    }
}
