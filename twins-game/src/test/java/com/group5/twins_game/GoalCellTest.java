package com.group5.twins_game;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GoalCellTest {
    @Test
    public void testConstructor(){
        GameHandler testGameHandler = new GameHandler();
        GoalCell testGoal = new GoalCell(new Positions(0, 0), new Player(null, "blue", testGameHandler.background2d));
        assert(!testGoal.solid);
        assertNotNull(testGoal.img);
    }

    @Test
    public void testHasItem() {
        GameHandler testGameHandler = new GameHandler();
        GoalCell testGoal = new GoalCell(new Positions(0, 0), new Player(null, "blue", testGameHandler.background2d));
        assert(!testGoal.hasItem());
    }
}
