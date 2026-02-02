package com.group5.twins_game;

import java.io.IOException;
import java.util.Objects;

/** a class of unblocked cells to place the Entities on
 * implemented via an array of entities
 *
 * @author Joseph Zhang
 * @author Winnie Chan
 *
 * @version 1.0
 * @see Cell
 * @see Entity
 * */

public class GrassCell extends Cell {
    NullEntity empty = new NullEntity();
    Entity[] holding = new Entity[3];
    int elementCount = 0;
    public boolean solid;

    /**Constructor of the Grass cell, in addition to super constructor
     * now sets the image to grass lands and set all entities to empty.
     *
     * @author Joseph Zhang
     * @author Winnie Chan
     * */
    public GrassCell(Positions pos) {
        super();
        this.solid = false;
        try {
            img = loader.readImg("/misc_entities/grass.png");
        } catch (IOException e) {
            System.out.println(e.getClass() + " is catching image error");
        }

        position = pos;
        for (int i = 0; i<3; i++) {
            holding[i] = empty;
        }
    }

    /** Used to detect if current cell has item on it
     *
     * @return true if cell holds any Ground item in itself
     *
     * @author Joseph Zhang
     * @author Winnie Chan
     * */
    public boolean hasItem() {
        for (int i = 0; i < 3; i++) {
            if (Objects.equals(this.getEntityID(i), "Coin") ||
                    Objects.equals(this.getEntityID(i), "Diamond") ||
                    Objects.equals(this.getEntityID(i), "Key") ||
                    Objects.equals(this.getEntityID(i), "Trap")) {
                return true;
            }
        }
        return false;

    }
}