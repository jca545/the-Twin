package com.group5.twins_game;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.lang.Math.round;


/**The door cell which leads to the goal room
 * will open upon collecting all the keys
 * and be available to get moved through
 *
 * @author Jordan Clough
 * @author Joseph Zhang
 * @author Winnie Chan
 *
 * @version 1.0
 * @see Cell
 * @see GameHandler
 * @see Entity
 * */
public class DoorCell extends Cell {
    ImgLoader loader = new ImgLoader();
    public BufferedImage imgDoorOpened;
    public BufferedImage imgGrass;

    /**Constructor of the door cell, setting itself
     * to be unable to get moved into load image loader
     * and load image from resources
     *
     * @param pos the Position class in which this
     *            DoorCell is going to be at
     *
     * @author Jordan Clough
     * @author Joseph Zhang
     * @author Winnie Chan
     * */
    public DoorCell(Positions pos) {
        super();
        this.solid = true;
        try {
            img = loader.readImg("/misc_entities/door-close.png");
            imgDoorOpened = loader.readImg("/misc_entities/door-open.png");
            imgGrass = loader.readImg("/misc_entities/grass.png");
        } catch (IOException e) {
            System.out.println(e.getClass() + " is catching image error");
        }

        position = pos;
        //position = initialPosition;
    }

    /** called when all keys are collected, opening the door
     *  and allowing movables to move through it
     *
     * @author Jordan Clough
     *
     * @see GameHandler
     * */
    public void unlockDoor() {
        solid = false;
    }

    /** error catching use, when object is attempting
     * to be inserted, it prints out an error message
     *
     * @author Joseph Zhang
     *
     * @see GameHandler
     * */
    public void insert(Entity entity) {
        //System.out.println("Insert to cell overFlow");
    }

    /** to draw the door on the game screen.
     *  depending on the state of the door,
     *  whether open or not will draw differently.
     *
     * @param graph2 graphic loader used to draw the image on.
     *
     * @author Joseph Zhang
     *
     * @see GameHandler
     * */
    @Override
    public void draw(Graphics2D graph2){
        if (!solid) {
            graph2.drawImage(imgGrass, (int)round(position.getDoublex()*24),
                    (int)round(position.getDoubley()*24),
                    24, 24, null);
            graph2.drawImage(imgDoorOpened, (int)round(position.getDoublex()*24),
                    (int)round(position.getDoubley()*24),
                    24, 24, null);
        } else {
            graph2.drawImage(img, (int)round(position.getDoublex()*24),
                    (int)round(position.getDoubley()*24),
                    24, 24, null);
        }
    }

    /**overwrite function, return false for having item
     *
     * @return false, for the Door cannot contain item
     *
     * @author Joseph Zhang
     * */
    public boolean hasItem() {
        return false;
    }

}
