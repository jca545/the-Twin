package com.group5.twins_game;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class EnemyTest {

    GameHandler gh = new GameHandler();
    Board board = new Board(gh.screenCol, gh.screenRow);

    @Test
    public void constructorTest() throws IOException {
        Enemy[] EnemyCol = new Enemy[3];
        double[] speeds = {0.07,0.05,0.03};
        for (int i = 0; i<3; i++)
        {
            EnemyCol[i] = new Enemy(new Positions(i+1,i+1),board,i);
            Cell cell = board.cellBoard[i+1][i+1];
            Entity entity = cell.holding[1];
            assertEquals("Enemy", entity.entityID);
            assertTrue(speeds[i] == EnemyCol[i].getsteps());
        }
    }

    @Test
    public void moveTest() throws IOException {
        Enemy testing = new Enemy(new Positions(5,5),board,0);
        //unlocked, to right
        moveAuto(testing, 6, 5, 5+testing.steps, 5);

        //unblocked, to left
        moveAuto(testing, 4, 5, 5, 5);

        //unlocked, go up
        moveAuto(testing, 5, 6, 5, 5+testing.steps);

        //unlocked go down
        moveAuto(testing, 5, 4, 5, 5);

        //blocked, trying to move
        testing.position.setPositions(5.04,5);
        board.insertCell(new WallCell(new Positions(6,5)),new Positions(6,5));
        moveAuto(testing,7,5,5.04,5);
    }
    private void moveAuto(Enemy testing, double px, double py, double ex, double ey)
    {
        testing.update(new Positions(px,py),board);
        assertTrue(testing.position.getDoublex() == new Positions(ex,ey).getDoublex());
    }


}
