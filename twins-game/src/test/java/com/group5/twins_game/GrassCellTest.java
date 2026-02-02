package com.group5.twins_game;

import org.junit.Test;

import static org.junit.Assert.*;

public class GrassCellTest {
    @Test
    public void testConstructor(){
        GrassCell testGrass = new GrassCell(new Positions(0, 0));
        assert(!testGrass.solid);
        assertNotNull(testGrass.img);
    }

    @Test
    public void testHasItem() {
        GrassCell testGrass = new GrassCell(new Positions(0, 0));
        assert(!testGrass.hasItem());
        try {
            testGrass.insert(new Coin(new Positions(0,0)));
        } catch (Exception ignore) {
            assert(false);
        }
        assert(testGrass.hasItem());
    }

}
