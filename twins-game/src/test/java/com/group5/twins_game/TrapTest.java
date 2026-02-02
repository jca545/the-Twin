package com.group5.twins_game;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

    public class TrapTest {
        @Test
        public void testTrap(){
            try {
                Trap testing = new Trap(new Positions(5, 5));
                assertEquals("Trap", testing.getEntityID());
                assertEquals(5, testing.position.getxPosition());
                assertEquals(5, testing.position.getyPosition());
                assertEquals(-10, testing.gains());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

