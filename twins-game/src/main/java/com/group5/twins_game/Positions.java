package com.group5.twins_game;

import static java.lang.Math.*;

/**Position class, made for abstraction and class invariant
 * x and y at least 0, x and y not off the screen.
 * build with double variables and corresponding index of cell
 * on board is determined by rounding the double.
 *
 * @author Jin Yang
 * @author Winnie Chan
 *
 * @version 1.0
 * @see Cell
 * @see Board
 * @see Entity
 * */

public class Positions
{
    private double xPosition;
    private double yPosition ;

    /**Constructor of a position data structure and its
     * overloaded method for setting x and y directly
     *
     * @author Jin Yang
     * @author Winnie Chan
     * */
    public Positions() {
        xPosition = 0;
        yPosition = 0;
    }
    public Positions(double x, double y) {
        xPosition = x;
        yPosition = y;
    }

    /**getter method  of x and y variables, returning a rounded integer
     * of the respective value, used to calculate the index of cell
     * on board
     *
     * @return rounded integer x or y value
     *
     * @author Jin Yang
     * @author Winnie Chan
     * */
    public int getxPosition() {
        return (int)round(xPosition);
    }
    public int getyPosition() {
        return (int)round(yPosition);
    }

    /**getter method  of x and y variables, returning the exact
     * double value without rounding
     *
     * @return exact double x and y value
     *
     * @author Jin Yang
     * @author Winnie Chan
     */
    public double getDoublex() {
        return xPosition;
    }
    public double getDoubley()
    {
        return yPosition;
    }

    /**setter method  of x variables
     * @param  newxPosition the x position to set the position to
     *
     * @author Jin Yang
     * @author Winnie Chan
     */
    public void setxPosition(double newxPosition) {
        this.xPosition = newxPosition;
    }

    /**setter method  of y variables
     *
     *  @param  newyPositon the y positon to set the position to
     *
     * @author Jin Yang
     * @author Winnie Chan
     */
    public void setyPosition(double newyPositon) {
        this.yPosition = newyPositon;
    }

    /**setter method of both x and y variables
     *
     * @param  x x positon to set the position to
     * @param  y y position to set the position to
     *
     * @author Jin Yang
     * @author Winnie Chan
     */
    public void setPositions(double x, double y){
        setxPosition(x);
        setyPosition(y);
    }

    public void setPositions(Positions pos)
    {
        setxPosition(pos.getDoublex());
        setyPosition(pos.getDoubley());
    }

    public void displacePosition(Positions displacement)
    {
        setxPosition(getDoublex() + displacement.getDoublex());
        setyPosition(getDoubley() + displacement.getDoubley());
    }

    // Debug use
    /**debug usage
     * print out the position's x and y value
     *
     * @author Winnie Chan
     */
    public void printPos(){
        System.out.println("  in position: x: " + xPosition + ", y: " + yPosition);
    }
}