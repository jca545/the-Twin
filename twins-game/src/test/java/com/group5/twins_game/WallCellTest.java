package com.group5.twins_game;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class WallCellTest {
    @Test
    public void testConstructor(){
        WallCell testWall = new WallCell(new Positions(0, 0));
        assert(testWall.solid);
        assertNotNull(testWall.img);
    }
    @Test
    public void testInsert(){
        WallCell testWall = new WallCell(new Positions(0, 0));
        try {
            testWall.insert(new Coin(new Positions(0,0)));
        } catch (Exception ignore) {
            assert(false);
        }
        for (int i=0; i<testWall.holding.length; i++) {
            assert(testWall.holding[i] instanceof NullEntity);
        }
    }

    @Test
    public void testHasItem() {
        WallCell testWall = new WallCell(new Positions(0, 0));
        assert(!testWall.hasItem());
    }
}
