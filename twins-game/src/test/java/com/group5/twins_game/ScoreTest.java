package com.group5.twins_game;

import org.junit.Test;
import static org.junit.Assert.*;

public class ScoreTest {
    @Test
    public void testConstructorAndGetPointsAndKeys() {
        Score score = new Score();
        assertEquals(10, score.getPoints());
        assertEquals(0, score.getKeysCollected());
    }

    @Test
    public void testAddPoints() {
        Score score = new Score();
        int firstPoints = score.getPoints();
        for(int i = 0; i < 10; i++) {
            firstPoints = score.getPoints();
            score.addPoints(3*i);
            assertEquals(firstPoints + 3*i, score.getPoints());
        }
    }
    @Test
    public void testGetKeysCollected() {
        Score score = new Score();
        int firstKeys = score.getKeysCollected();
        for(int i = 0; i < 10; i++) {
            firstKeys = score.getKeysCollected();
            score.addKeysCollected(i);
            assertEquals(firstKeys + i, score.getKeysCollected());
        }
    }
}
