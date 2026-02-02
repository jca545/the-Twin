package com.group5.twins_game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.lang.Math.*;

/**
 * Abstract class of all entity that can be place on map
 *
 * @author Jin Yang
 * @author Joseph Zhang
 * @author Winnie Chan
 *
 * @version 1.0
 * @see Coin
 * @see Diamond
 * @see Enemy
 * @see GroundItem
 * @see Key
 * @see Movable
 * @see NullEntity
 * @see Player
 * @see Trap
 */
public class Entity {
    protected String entityID = null;
    public Positions position = new Positions();
    public BufferedImage img;
    ImgLoader loader = new ImgLoader();

    /**
     * To determine which Entity this is
     *
     * @return the Entity type in String
     *
     * @author Joseph Zhang
     */
    public String getEntityID() {return entityID;}

    /**
     * Draw this entity on display map
     *
     * @param graph2 used to draw out onto the game window
     *
     * @author Joseph Zhang
     * @author Winnie Chan
     */
    public void draw(Graphics2D graph2){
        graph2.drawImage(img, (int)round(position.getDoublex()*24),
                (int)round(position.getDoubley()*24),
                24, 24, null);
    }

    /**
     * Delete this entity from the board display
     * and remove this entity from its cell
     *
     * @param graph2 used to draw out onto the game window
     * @param entity the entity to be erased
     *
     * @author Joseph Zhang
     * @author Winnie Chan
     */
    public void erase(Graphics2D graph2){
        try {
            img = loader.readImg("/misc_entities/grass.png");
        } catch (IOException e) {
            System.out.println(e.getClass() + " is catching image error");
        }
        graph2.drawImage(img, position.getxPosition()*24, position.getyPosition()*24,
                24, 24, null);
    }

    /**
     * Gives the amount of score gain or lose
     *
     * @return amount of score positive when gained,
     * negative when lose
     *
     * @author Joseph Zhang
     * @author Winnie Chan
     */
    public int gains(){
        return 0;
    }

}
