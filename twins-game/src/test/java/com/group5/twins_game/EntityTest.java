package com.group5.twins_game;

import org.junit.Test;

import static org.junit.Assert.*;

public class EntityTest {
    @Test
    public void testGetEntityID() {
        Entity testEntity = new Entity();
        assertNull(testEntity.getEntityID());
    }

    @Test
    public void testDraw() {
        GameHandler testGameHandler = new GameHandler();
        Entity testEntity = new Entity();
        testEntity.draw(testGameHandler.background2d);

        assertNull(testEntity.img);
    }

    @Test
    public void testErase(){
        GameHandler testGameHandler = new GameHandler();
        Entity testEntity = new Entity();
        testEntity.position = new Positions(0,0);
        testEntity.erase(testGameHandler.background2d);

        assertNotNull(testEntity.img);
    }

    @Test
    public void testGains(){
        // NOTE SINCE GROUNDITEM IS ABSTRACT, WE CAN'T TEST IT DIRECTLY (leads to coverage less than 100)
        Entity testEntity = new Entity();
        assertEquals(0, testEntity.gains());
    }
}
