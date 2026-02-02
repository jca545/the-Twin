package com.group5.twins_game;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class EnemyCollectionTest {


    GameHandler gh = new GameHandler();
    Board board = new Board(gh.screenCol, gh.screenRow);
    @Test
    public void makeEnemyTest() throws IOException {
        EnemyCollection testing = new EnemyCollection();
        double[] speeds = {0.07,0.05,0.03};
        for (int i = 0; i <3; i++) {
            testing.makeEnemy(new Positions(i+1, i+1), board, i);
            Cell cell = board.cellBoard[i+1][i+1];
            Entity entity = cell.holding[1];
            assertEquals("Enemy", entity.entityID);
            assertTrue(testing.enemyArr[i].getsteps() == speeds[i]);
            assertTrue(testing.getHolding() == i+1);
        }
    }

    @Test
    public void updateTest() throws IOException {
        EnemyCollection testing = new EnemyCollection();
        for (int i = 0; i <3; i++) {
            testing.makeEnemy(new Positions(i+1, i+1), board, i);
        }
        testing.updateIter(new Positions(10,2),board);
        for (int i = 0; i<3; i++)
        {
            assertTrue(testing.enemyArr[i].position.getDoublex() == i+1+testing.enemyArr[i].getsteps());
        }
    }
    private void moveAuto(Enemy testing, double px, double py, double ex, double ey)
    {
        testing.update(new Positions(px,py),board);
        assertTrue(testing.position.getDoublex() == new Positions(ex,ey).getDoublex());
    }
}
