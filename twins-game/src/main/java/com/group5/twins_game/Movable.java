package com.group5.twins_game;

import static java.lang.Math.round;

/**
 * This class will store the variables that
 * all moving entity needs including both
 * Player and the moving enemy
 *
 * @author Jin Yang
 * @author Winnie Chan
 *
 * @version 1.0
 * @see Enemy
 * @see Player
 */
public class Movable extends Entity{
    // steps = how far will this move for each key pressed
    double steps;

    // direction that this movable entity is facing
    public Direction direct;

    // to hold the images for this movable entity
    public DirectionImg dImg = new DirectionImg();

    double squareRadius;

    public double getsteps()
        {
            return steps;
        }

    protected boolean checkUnblocked(Board cellBd, Positions nextPos, double squareRadius)
    {
        double maxX = nextPos.getDoublex() +squareRadius;
        double minX = nextPos.getDoublex() -squareRadius;
        double maxY = nextPos.getDoubley() +squareRadius;
        double minY = nextPos.getDoubley() -squareRadius;

        Positions topleftCell = new Positions((int)round(minX),(int)round(minY));
        Positions toprightCell = new Positions((int)round(maxX),(int)round(minY));
        Positions bottomleftCell = new Positions((int)round(minX),(int)round(maxY));
        Positions bottomrightCell = new Positions((int)round(maxX),(int)round(maxY));

        return (cellBd.unblockedCell(topleftCell) &&cellBd.unblockedCell(toprightCell)
                &&cellBd.unblockedCell(bottomleftCell)&&cellBd.unblockedCell(bottomrightCell));
    }

    //Up, Down, Left, Right;
    protected Positions moveInDirection(Direction dir)
    {
        direct = dir;
        Positions oPos = new Positions();
        oPos.setPositions(position);
        double xDis = dir.getXDisplacement() * steps;
        double yDis = dir.getYDisplacement() * steps;
        oPos.displacePosition(new Positions(xDis,yDis));
        return oPos;
    }
}
