package com.group5.twins_game;

import java.awt.*;

/**
 * A Factory class that create cells in given position,
 * display this cell, and insert this cell into game board
 *
 * @author Joseph Zhang
 * @author Winnie Chan
 *
 * @version 1.0
 * @see GameHandler
 * @see MapBuilder
 * @see Cell
 * @see DoorCell
 * @see GoalCell
 * @see GrassCell
 * @see WallCell
 */
public class MapFactory {
    /**
     * creates a Wall cell
     * @param pos the position to set this wall cell
     * @param cellBoard the game board
     * @param background used to draw out onto the game window
     *
     * @author Joseph Zhang
     * @author Winnie Chan
     */
    public void makeWallCell(Positions pos, Board cellBoard, Graphics2D background) {
        WallCell wallCell;
        wallCell = new WallCell(pos);
        cellBoard.insertCell(wallCell, pos);
        wallCell.draw(background);
    }

    /**
     * creates a Grass cell
     * @param pos the position to set this grass cell
     * @param cellBoard the game board
     * @param background used to draw out onto the game window
     *
     * @author Joseph Zhang
     * @author Winnie Chan
     */
    public void makeGrassCell(Positions pos, Board cellBoard, Graphics2D background) {
        GrassCell grass;
        grass = new GrassCell(pos);
        cellBoard.insertCell(grass, pos);
        grass.draw(background);
    }

    /**
     * creates a Door cell
     * @param pos the position to set this door cell
     * @param cellBoard the game board
     * @param background used to draw out onto the game window
     *
     * @author Joseph Zhang
     * @author Winnie Chan
     */
    public DoorCell makeDoorCell(Positions pos, Board cellBoard, Graphics2D background) {
        DoorCell door;
        door = new DoorCell(pos);
        cellBoard.insertCell(door, pos);
        door.draw(background);
        return door;
    }

    /**
     * creates a Goal cell
     * @param pos the position to set this goal cell
     * @param cellBoard the game board
     * @param background used to draw out onto the game window
     *
     * @author Joseph Zhang
     * @author Winnie Chan
     */
    public GoalCell makeGoalCell(Positions pos, Board cellBoard, Graphics2D background, Player p) {
        GoalCell goal;
        goal = new GoalCell(pos, p);
        cellBoard.insertCell(goal, pos);
        goal.draw(background);
        return goal;
    }
}
