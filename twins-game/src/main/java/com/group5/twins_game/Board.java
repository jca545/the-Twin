package com.group5.twins_game;

/**the board of which all items and characters are placed on
 * implemented via a 2d array of cells
 * continuously updated throughout the game to reflect the location of cells
 * and entities on the cells
 *
 * @author Jin Yang
 * @author Jordan Clough
 * @author Joseph Zhang
 * @author Winnie Chan
 *
 * @version 1.0
 * @see Cell
 * @see Entity
 * */
public class Board {
    Cell[][] cellBoard;
    DoorCell door;
    GoalCell gCell;
    Positions pos;

    /**constructor of the Board, initializing the array with all grass cells
     * Taking in the x dimension and y dimension of the array to be created
     * @param cols  the x dimension of the board,
     *              representing how many columns to be created
     * @param rows  the y dimension of the board,
     *              representing how many rows to be created
     *
     * @author Jin Yang
     * @author Jordan Clough
     * @author Joseph Zhang
     * @author Winnie Chan
     *
     * @see GrassCell
     * @see Cell
     * @see MapBuilder
     * */
    public Board(int cols, int rows) {
        cellBoard = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                pos = new Positions(j, i);
                this.insertCell(new GrassCell(pos), pos);
            }
        }
    }

    /** Inserting the cell into a location on array
     *  overwriting the original cell on array
     *
     * @param cell  the cell of which is to be placed into the array
     * @param pos   the location of which cell is to be put in
     *
     * @author Joseph Zhang
     *
     * @see Cell
     * */
    public void insertCell(Cell cell, Positions pos) {
        int row = pos.getyPosition();
        int col = pos.getxPosition();
        cellBoard[row][col] = cell;
    }

    /** Inserting an entity into the cell on location
     *  calling insert on the cell of location
     *
     * @param entity    the entity of which is to be
     *                  placed into the cell
     * @param pos       the location marking the cell of
     *                  which entity is to be placed in
     *
     * @author Jin Yang
     * @author Joseph Zhang
     * @author Winnie Chan
     *
     * @see GrassCell
     * */
    public void insert(Entity entity, Positions pos) {
        int row = pos.getyPosition();
        int col = pos.getxPosition();
        cellBoard[row][col].insert(entity);
    }

    /** to move an item from one cell into another
     *  done through removing the item from the original
     *  then inserting it into the cell of destination
     *
     *
     * @param entity    the entity of which is to be placed into the cell
     * @param newPos    the location to place the entity into
     *
     * @author Jin Yang
     * @author Joseph Zhang
     * @author Winnie Chan
     *
     * @see GrassCell
     * */
    public void move(Entity entity, Positions newPos) {
        if (newPos.getxPosition()>=0 && newPos.getxPosition()<32
                && newPos.getyPosition()>=0 && newPos.getyPosition()<24) {
            int row = newPos.getyPosition();
            int col = newPos.getxPosition();
            int orow = entity.position.getyPosition();
            int ocol = entity.position.getxPosition();
            cellBoard[orow][ocol].remove(entity);
            cellBoard[row][col].insert(entity);
        }
    }

    /** to check if a cell on array is considered to be blocked
     *  or should get moved into
     *
     * @param pos    the location of the cell to check
     * @return       return true if the cell at position is unblocked, or can be gotten into
     *               in other words, (is open door or is a grass cell)
     *               return false if the cell is blocked
     *               is a closed door or is a wall
     *
     * @author Jin Yang
     * @author Joseph Zhang
     * @author Winnie Chan
     *
     * @see GrassCell
     * @see WallCell
     * @see DoorCell
     * */
    // true: not a wall/ is opened-door
    // false: a wall/closed-door
    public boolean unblockedCell(Positions pos) {
        int x = pos.getxPosition();
        int y = pos.getyPosition();
        return (!cellBoard[y][x].isSolid());
    }

    /** to check if a cell on array is holding an item
     *  should only be passed the position of a GrassCell
     *
     * @param pos    the location of the cell to check
     * @return       return true if the cell at position has item, or can be gotten into
     *               return false if the cell does not
     *
     * @author Jin Yang
     * @author Joseph Zhang
     * @author Winnie Chan
     *
     * @see GrassCell
     * */
    public boolean itemInCell(Positions pos) {
        int x = pos.getxPosition();
        int y = pos.getyPosition();

        return (cellBoard[y][x].hasItem());
    }

    /** to check a location on array is open for setting an item in
     *  used while setting the location of the items
     *
     *
     * @param pos    the location of the cell to check
     * @return       return true only if
     *               cell on location is a GrassCell
     *               AND cell does not already hold an item
     *               AND cell is not one that is in the goal room
     *               return false if the cell fails any of the above
     *
     * @author Winnie Chan
     * @see GrassCell
     * @see MapBuilder
     * */
    public boolean availableToPut(Positions pos){
        // make sure nothing put in goal room
        boolean inRoom = false;
        int x = pos.getxPosition();
        int y = pos.getyPosition();
        // Goal room index (that three grassCell)
        if ((x == 1 || x == 2 || x == 3) && (y == 1 || y == 2)){
            inRoom = true;
        }
        return (unblockedCell(pos) && !itemInCell(pos) && !inRoom);//
    }

}
