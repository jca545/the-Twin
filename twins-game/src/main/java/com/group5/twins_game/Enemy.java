package com.group5.twins_game;
import java.io.IOException;
import java.lang.Math;

import static com.group5.twins_game.Direction.*;
import static java.lang.Math.abs;


/**moving enemies extending movable and
 * chases after the player
 *
 * @author Jin Yang
 * @author Jordan Clough
 * @author Winnie Chan
 * @version 1.0
 * @see Entity
 * @see Movable
 * */
public class Enemy extends Movable{

    boolean caught = false;

    int frameCount = 0;
    /** constructor of Enemy
     *  creating an enemy and setting it on board
     *  setting its speed according to its id
     *  loading image
     *
     * @author Jin Yang
     * @author Joseph Zhang
     * @author Winnie Chan
     * @param pos position to the enemy on
     * @param cellBd cellBoard to insert the enemy into
     * @param ID    used to identity the enemy, (1,2,3)
     * @see GameHandler
     * */
    public Enemy(Positions pos, Board cellBd, int ID) throws IOException {
        this.position= pos;
        if (ID == 0){
            steps = 0.07;
        } else if (ID == 1){
            steps = 0.05;
        } else {
            steps = 0.03;
        }
        squareRadius = 0.4;
        dImg.upImg = loader.readImg("/enemies/enemyUp.png");
        dImg.downImg = loader.readImg("/enemies/enemyDown.png");
        dImg.leftImg = loader.readImg("/enemies/enemyLeft.png");
        dImg.rightImg = loader.readImg("/enemies/enemyRight.png");
        img = dImg.upImg;
        entityID = "Enemy";
        cellBd.insert(this,pos);
    }

    /** update the position of enemy
     *  depending on the position of the player, the enemy
     *  will move to close the distance by setting the direction
     *  and setting the new position according to the step size
     *
     * @author Jin Yang
     * @author Joseph Zhang
     * @author Winnie Chan
     * @param playerPos position of player, used to calculate
     *                  the next step the enemy will take
     * @param cellBd cellBoard of which the enemy will be moving across
     * @see GameHandler
     * */
    public void update(Positions playerPos, Board cellBd) {
        double Xdif = playerPos.getDoublex() - position.getDoublex();
        double Ydif = playerPos.getDoubley() - position.getDoubley();
        if (abs(Xdif)<0.3 && abs(Ydif) < 0.3) {
            caught = true;
        }
        else
        {
            movetoPlayer(Xdif, Ydif, cellBd);
        }
    }

    private void movetoPlayer(double Xdif, double Ydif, Board cellBd)
    {
        Positions nextPos;
        if (Math.abs(Xdif) > Math.abs(Ydif)) {
            if (Xdif >0) {
                nextPos = moveInDirection(Right);
            } else {
                nextPos = moveInDirection(Left);
            }
        } else {
            if (Ydif > 0) {
                nextPos = moveInDirection(Down);
            } else {
                nextPos = moveInDirection(Up);
            }
        }
        move(cellBd, nextPos);
    }


    /** helper method move, used to compact the code and determine
     *  if the next move can be made, and what next cell to put
     *  the enemy into. Call function to change the sprite of
     *  enemy according to the direction
     *
     * @author Jin Yang
     * @author Winnie Chan
     * @param cellBd cellBoard of which the enemy will be moving across
     *               also used to detect if the cell on board is blocked
     * @see GameHandler
     * */
    private void move(Board cellBd, Positions newPosition) {
        Positions cellPos;
        cellPos = new Positions(newPosition.getxPosition(), newPosition.getxPosition());

        if (checkUnblocked(cellBd,newPosition, squareRadius)) {
            //if (cellBd.unblockedCell(cellPos,nextEntity)){
            cellBd.move(this, cellPos);
            position.setPositions(newPosition.getDoublex(), newPosition.getDoubley());
        }
            // Change the character image to correspond side
        changeImgDirection();

    }



    /** helper method, Change the sprite of enemy according to
     *  the direction
     *
     * @author Jin Yang
     * */
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
