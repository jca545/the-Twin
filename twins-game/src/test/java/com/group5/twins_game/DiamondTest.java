package com.group5.twins_game;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

public class DiamondTest {

    @Test
    public void testConstructorAndEntityID() throws IOException {
        Positions pos = new Positions(5.5, 6.6);
        Diamond diamond = new Diamond(pos);

        // Test entityID set in constructor
        assertEquals("Diamond", diamond.getEntityID());
    }


    @Test
    public void testConstructorAndGains() throws IOException {
        Positions pos = new Positions(5.5, 6.6);
        Diamond diamond = new Diamond(pos);

        // Test gains method
        assertEquals(20, diamond.gains());
    }

    @Test
    public void testConstructorAndPosition() throws IOException {
        Positions pos = new Positions(5.5, 6.6);
        Diamond diamond = new Diamond(pos);

        // Test entityID set in constructor
        assertEquals(pos.getxPosition(), diamond.position.getxPosition());

        assertEquals(pos.getyPosition(), diamond.position.getyPosition());
    }

}
