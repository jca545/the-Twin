package com.group5.twins_game;

/**
 * The Interface of all Ground items that
 * can be place on Grass cell
 *
 * @author Joseph Zhang
 *
 * @version 1.0
 * @see Coin
 * @see Diamond
 * @see Key
 * @see Trap
 * @see Entity
 * @see Cell
 * @see GrassCell
 */
public abstract class GroundItem extends Entity {
    /**
     * The interface of how all GroundItem
     * effects the player
     *
     * @return the amount of score/key
     * effecting the player
     *
     * @author Joseph Zhang
     */
    public abstract int gains();

}
