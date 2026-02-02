package com.group5.twins_game;

import static java.lang.Math.round;

import java.awt.*;
import java.awt.image.BufferedImage;
/**the cells to place the Entities on
 * implemented via an array of entities
 * updates when an item is placed or removed from it
 *
 * @author Jin Yang
 * @author Jordan Clough
 * @author Joseph Zhang
 * @author Winnie Chan
 *
 * @version 1.0
 * @see GrassCell
 * @see WallCell
 * @see DoorCell
 * @see Entity
 * */
public abstract class Cell {
    NullEntity empty = new NullEntity();
    Entity[] holding = new Entity[3];
    public boolean solid = false;
    ImgLoader loader;
    public BufferedImage img;
    Positions position;

    /**Constructor of the cell, setting all entities to a NullEntity
     * then create the image loader
     *
     * @author Jin Yang
     * @author Joseph Zhang
     * @author Winnie Chan
     * */
    public Cell() {
        for (int i = 0; i<3; i++) {
            holding[i] = empty;
        }
        this.loader = new ImgLoader();
    }

    // Debug use
    /** printing out all the Entity on the cell in order
     * used during debug, have no effect in game
     *
     * @author Joseph Zhang
     * @see Entity
     * */
    public void print() {
        for (int i = 0; i < 3; i++) {
            System.out.println(holding[i].getEntityID());
        }
    }
    /**default and abstract function for determining if cell is movable
     * @return false, will be overwritten in Cell implementations
     *
     * @author Joseph Zhang
     *
     * @see Entity
     * */
    public boolean isSolid() {
        return solid;
    }

    public void draw(Graphics2D graph2){
        graph2.drawImage(img, (int)round(position.getDoublex()*24),
                (int)round(position.getDoubley()*24),
                24, 24, null);
    }

    /**default and abstract function for determining if cell has on item on it
     *
     * @author Joseph Zhang
     *
     * @see GroundItem
     * */
    public abstract boolean hasItem();

    /** Inserting the entity in question into the cell
     *  where if the entity is a movable object, it is placed in index 1
     *  if is an item, is placed in index 0
     *
     * @param entity   entity of which to put in
     *
     * @author Joseph Zhang
     *
     * @see Cell
     * */
    public void insert(Entity entity) {
        if (entity instanceof Movable) {
            holding[1] = entity;
        } else {
            holding[0] = entity;
        }
    }

    /** Inserting the entity from the cell by setting the corresponding
     *  position to 0
     *  if object is a Movable, index 1 is removed
     *  otherwise, 0 is removed
     *
     * @param entity   entity of which to put in
     *
     * @author Joseph Zhang
     *
     * @see Cell
     * */
    public void remove(Entity entity) {
        if (entity instanceof Movable) {
            holding[1] = empty;
        } else {
            holding[0] = empty;
        }
    }
    /** returns the string entityID for identifying the type of entity
     *  using the index of the entity
     *
     * @param num the index of the item to be checked
     * @return string value of the entity ID
     *
     * @author Jin Yang
     * @author Joseph Zhang
     *
     * @see Cell
     * */
    public String getEntityID(int num)
    {
        return(holding[num].entityID);
    }


}