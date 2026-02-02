package com.group5.twins_game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NullEntityTest {
    @Test
    public void testNull(){
        NullEntity testing = new NullEntity();
        assertEquals("NullEntity", testing.getEntityID());

    }
}
