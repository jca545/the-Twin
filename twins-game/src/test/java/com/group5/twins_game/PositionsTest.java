package com.group5.twins_game;

import org.junit.Test;
import static org.junit.Assert.*;

public class PositionsTest {

    @Test
    public void testDefaultConstructor() {
        Positions pos = new Positions();
        assertEquals(0, pos.getxPosition());
        assertEquals(0, pos.getyPosition());
        assertEquals(0.0, pos.getDoublex(), 0.001);
        assertEquals(0.0, pos.getDoubley(), 0.001);
    }

    @Test
    public void testParameterizedConstructor() {
        Positions pos = new Positions(10.5, 11.6);
        assertEquals(11, pos.getxPosition());
        assertEquals(12, pos.getyPosition());
        assertEquals(10.5, pos.getDoublex(), 0.001);
        assertEquals(11.6, pos.getDoubley(), 0.001);
    }

    @Test
    public void testSetters() {
        Positions pos = new Positions(1000.5, 1111.6);
        pos.setxPosition(7.7);
        pos.setyPosition(8.8);
        assertEquals(8, pos.getxPosition());
        assertEquals(9, pos.getyPosition());
        assertEquals(7.7, pos.getDoublex(), 0.001);
        assertEquals(8.8, pos.getDoubley(), 0.001);
    }
    @Test
    public void testWithDefaultSetters() {
        Positions pos = new Positions();
        pos.setxPosition(1.1);
        pos.setyPosition(2.2);
        assertEquals(1, pos.getxPosition());
        assertEquals(2, pos.getyPosition());
        assertEquals(1.1, pos.getDoublex(), 0.001);
        assertEquals(2.2, pos.getDoubley(), 0.001);
    }

    @Test
    public void testSetTogetherWithDefaultPositions() {
        Positions pos = new Positions();
        pos.setPositions(4.4, 5.5);
        assertEquals(4, pos.getxPosition());
        assertEquals(6, pos.getyPosition());
        assertEquals(4.4, pos.getDoublex(), 0.001);
        assertEquals(5.5, pos.getDoubley(), 0.001);
    }    @Test
    public void testSetTogetherPositions() {
        Positions pos = new Positions(99.99 , 33.333);
        pos.setPositions(9.89, 10.111);
        assertEquals(10, pos.getxPosition());
        assertEquals(10, pos.getyPosition());
        assertEquals(9.89, pos.getDoublex(), 0.001);
        assertEquals(10.111, pos.getDoubley(), 0.001);
    }
}
