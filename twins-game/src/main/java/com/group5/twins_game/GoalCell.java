package com.group5.twins_game;

/**The cell of which the sister(the goal) is set on
 * win the game when player stepped onto it
 *
 * @author Joseph Zhang
 * @author Winnie Chan
 *
 * @version 1.0
 * @see Cell
 * */
public class GoalCell extends Cell {
    /** Constructor of the goal cell, makes an unblocked cell
     *  and set the image to the sister
     *
     * @param pos the position to set the cell on
     * @param player extracting the sis img from the play class
     *               used to set the image of the cell
     *
     * @author Joseph Zhang
     * @author Winnie Chan
     * */
    public GoalCell(Positions pos, Player player) {
        super();
        this.solid = false;
        img = player.sisImg;

        position = pos;
    }

    /**
     * check if this cell has item
     *
     * @return false always because nothing can be put on GoalCell
     *
     * @author Joseph Zhang
     */
    public boolean hasItem() {
        return false;
    }
}
