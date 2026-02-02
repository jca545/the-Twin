package com.group5.twins_game;

import java.awt.*;
import java.io.IOException;
/** An array structure holding references to all Enemies made
 *  responsible for iterating through all of them to call for
 *  update or redraw
 *
 * @author Jin Yang
 * @author Winnie Chan
 *
 * @version 1.0
 * @see Entity
 * @see Enemy
 * */
public class EnemyCollection {

    Enemy[] enemyArr = new Enemy[5];
    int holding = 0;

    boolean caught = false;

    /** creating an enemy and inserting it into cell on position
     *  incrementing the number of enemies
     *
     * @param pos the position to set the enemy on
     * @param cellBd the board to set the enemy on
     * @param ID the ID associated with the current enemy
     *           used to determine the step size attribute
     *           of it
     *
     * @author Jin Yang
     * @author Winnie Chan
     * */
    public void makeEnemy(Positions pos, Board cellBd, int ID) throws IOException {
        enemyArr[holding] = new Enemy(pos, cellBd, ID);
        holding++;
    }
    public int getHolding()
        {
            return holding;
        }

    /** iterate through all the enemies calling update and having
     *  them move in the direction of player.
     *
     * @param playerPos the position of player
     *                  passing into the enemy update method to determine
     *                  the direction the enemy should move in
     * @param cellBd the board for enemy to move across
     *
     * @author Jin Yang
     * @author Winnie Chan
     * */
    public void updateIter(Positions playerPos, Board cellBd) {
        for (int i = 0; i<holding; i++) {
            enemyArr[i].update(playerPos,cellBd);
            if (enemyArr[i].caught) {
                caught = true;
            }
        }
    }

    /** iterate through all the enemies calling draw and having
     *  them drawn out on the game window
     *
     * @param graph2 used to draw out onto the game window
     *
     * @author Jin Yang
     * */
    public void drawIter(Graphics2D graph2)
    {
        for (int i = 0; i<holding; i++)
        {
            enemyArr[i].draw(graph2);
        }
    }

    /** iterate through all the enemies and print the position of it
     *  used during debug
     *
     * @author Jin Yang
     * @author Winnie Chan
     * */
    public void printEnemyLocation() {
        for (int i = 0; i < holding; i++) {
            System.out.println("enemy at x = " + enemyArr[i].position.getxPosition()+ " , y= " + enemyArr[i].position.getyPosition());
        }
    }
}
