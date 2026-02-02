package com.group5.twins_game;

import java.io.IOException;


/**Blocked wall cell, cannot hold ground item and movable cannot pass through it
 *
 * @author Winnie Chan
 * @author Joseph Zhang
 * @version 1.0
 * @see Cell
 * */
public class WallCell extends Cell {

    /**Constructor of the Wall Cell, setting itself to be solid
     * and blocked and retrieving image from resources
     *
     * @author Joseph Zhang
     * @author Winnie Chan
     * @param pos the position the cell is set on
     * */
    public WallCell(Positions pos) {
        super();
        this.solid = true;
        try {
            img = loader.readImg("/misc_entities/rock.png");
        } catch (IOException e) {
            System.out.println(e.getClass() + " is catching image error");
        }
        position = pos;
    }

    /**overwrite function, return false for having item
     *
     * @author Joseph Zhang
     * @return false, for the Wall cannot contain item
     * */
    public boolean hasItem() {
        return false;
    }

    /** error catching use, when object is attempting to be inserted
     *  prints out an error message
     *
     * @author Joseph Zhang
     * @see GameHandler
     * */
    public void insert(Entity entity) {
        //System.out.println("Insert to cell overFlow");
    }

}
