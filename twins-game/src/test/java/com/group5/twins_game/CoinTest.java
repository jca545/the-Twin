package com.group5.twins_game;

import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class CoinTest {
    @Test
    public void testCoin(){
        Positions pos = new Positions(7, 3);
        try {
            Coin coin = new Coin(pos);
            assertEquals("Coin", coin.getEntityID());
            assertEquals(7, coin.position.getxPosition());
            assertEquals(3, coin.position.getyPosition());
            assertEquals(5, coin.gains());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
