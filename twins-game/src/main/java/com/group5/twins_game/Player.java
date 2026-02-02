package com.group5.twins_game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static com.group5.twins_game.Direction.*;


/**
 * A class for all functionalities of a player
 *
 * @author Jin Yang
 * @author Jordan Clough
 * @author Joseph Zhang
 * @author Winnie Chan
 *
 * @version 1.0
 * @see Board
 * @see Cell
 * @see Entity
 * @see Key
 * @see Movable
 */
public class Player extends Movable{
    KeyControl keys;
    String who;
    Graphics2D background;
    double maxX, maxY, minX, minY;
    Score score;
    public BufferedImage sisImg;
    int frameCount = 0;

    /**
     * Constructor of this Player class
     * Will set the character's initial x, y position,
     * along with the amount of pixels that this player
     * moves within each frame after any key pressed.
     * This player can choose either to be the
     * red-hair character, or the blue-hair character.
     *
     * @param kCtrl The KeyControl class that controls
     *              any key pressed or released
     * @param color The chosen character's color
     * @param bg used to draw out onto the game window
     *
     * @author Jin Yang
     * @author Jordan Clough
     * @author Joseph Zhang
     * @author Winnie Chan
     */
    public Player(KeyControl kCtrl, String color, Graphics2D bg){
        this.keys = kCtrl;
        this.who = color;
        this.score = new Score();
        position.setPositions(20, 10);
        steps = 0.12;
        loader = new ImgLoader();
        setImg();
        direct = Down;
        img = dImg.downImg;
        entityID = "Player";
        background = bg;
        squareRadius = 0.4;
    }

    /**
     * Set this player's display image that is
     * in the path of where the image is located
     *
     * @author Jin Yang
     * @author Winnie Chan
     */
    private void setImg(){
        try {
            if (Objects.equals(this.who, "red")) {
                dImg.upImg = loader.readImg("/characters/redUp.png");
                dImg.downImg = loader.readImg("/characters/redDown.png");
                dImg.leftImg = loader.readImg("/characters/redLeft.png");
                dImg.rightImg = loader.readImg("/characters/redRight.png");
                sisImg = loader.readImg("/characters/blueDown.png");
            } else {
                dImg.upImg = loader.readImg("/characters/blueUp.png");
                dImg.downImg = loader.readImg("/characters/blueDown.png");
                dImg.leftImg = loader.readImg("/characters/blueLeft.png");
                dImg.rightImg = loader.readImg("/characters/blueRight.png");
                sisImg = loader.readImg("/characters/redDown.png");
            }
        } catch (IOException e) {
            System.out.println(e.getClass() + " is catching image error");
        }
    }

    /**
     * When a key is pressed, change this player image
     * to face the direction that user pressed, and
     * display with this player step toward that direction
     * if available to move over.
     * Update the board with deleting the collided entity,
     * erase that entity from display.
     *
     * @author Jin Yang
     * @author Joseph Zhang
     * @author Winnie Chan
     */
    public void update(Board cellBd){
        frameCount++;
        if(frameCount >= 60) {
            frameCount = 0;
        }

        if (keys.pressedAny()) {
            Positions nextPos = keyDetect(keys,position);
            Positions cellPos = new Positions(nextPos.getxPosition(), nextPos.getyPosition());
            Cell nextCell = cellBd.cellBoard[nextPos.getyPosition()][nextPos.getxPosition()];
            Entity nextEntity = nextCell.holding[0];

            if (checkUnblocked(cellBd, nextPos, squareRadius)) {
                cellBd.move(this, cellPos);
                position.setPositions(nextPos);
                // When the door is opened
                if (nextCell instanceof DoorCell){
                    // change to open-door image
                    nextCell.draw(background);
                }
            }

            // Change the character image to correspond side
            changeImgDirection();

            // When Walk into cell that got GroundItem
            if (cellBd.itemInCell(position)) {
                // make the image disappear
                nextEntity.erase(background);

                // Gain corresponding points
                if (nextEntity instanceof Coin || nextEntity instanceof Diamond || nextEntity instanceof Trap) {
                    score.addPoints(nextEntity.gains());
                } else {
                    score.addKeysCollected(nextEntity.gains());
                }

                // remove this groundItem from this cell
                nextCell.remove(nextEntity);
            }
        }
    }

    private Positions keyDetect(KeyControl keys, Positions pos)
    {
        Positions nextPos;
        if (keys.upPressed) {
            nextPos = moveInDirection(Up);
        } else if (keys.downPressed) {
            nextPos = moveInDirection(Down);
        } else if (keys.leftPressed) {
            nextPos = moveInDirection(Left);
        } else if (keys.rightPressed) {
            nextPos = moveInDirection(Right);
        } else
        {
            nextPos = position;
        }
        return nextPos;
    }

    /**
     * A boolean function that returns is this player is at goal
     * @param gCell the GoalCell class which is the goal cell of this game
     * @return True when this player is at goal, false otherwise
     *
     * @author Jordan Clough
     */
    public boolean isAtGoal(GoalCell gCell){
        return (this.position.getDoublex() > gCell.position.getxPosition() - 1
                && this.position.getDoublex() < gCell.position.getxPosition() + 1
                && this.position.getyPosition() == gCell.position.getyPosition());
    }

    /**
     * Changes the image for display to the
     * corresponding side's image
     *
     * @author Jin Yang
     * @author Winnie Chan
     */
    private void changeImgDirection(){
        if (direct == Up){
            img = dImg.upImg;
        } else if (direct == Down){
            img = dImg.downImg;
        } else if (direct == Left){
            img = dImg.leftImg;
        } else {
            img = dImg.rightImg;
        }
    }


}
