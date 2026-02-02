package com.group5.twins_game;

import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class KeyTest {
    @Test
    public void testKey_with_pos(){
        Positions pos = new Positions(7, 3);
        try {
            Key key = new Key(pos);
            assertEquals("Key", key.getEntityID());
            assertEquals(7, key.position.getxPosition());
            assertEquals(3, key.position.getyPosition());
            assertEquals(1, key.gains());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testKey_without_pos(){
        try {
            Key key = new Key();
            assertEquals("Key", key.getEntityID());
            assertEquals(1, key.gains());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
