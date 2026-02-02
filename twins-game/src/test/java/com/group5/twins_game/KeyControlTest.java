package com.group5.twins_game;

import org.junit.Test;

import java.awt.event.KeyEvent;

import static org.junit.Assert.*;

public class KeyControlTest {
    GameHandler gh = new GameHandler();
    KeyControl keys = new KeyControl(gh, gh.background2d);

    @Test
    public void testCharStateBlue(){
        gh.gameState = gh.charactersState;
        KeyEvent e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_A, 'A');
        keys.keyPressed(e);
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_ENTER, 'E');
        keys.keyPressed(e);
        assertEquals("blue", gh.which_character);
    }
    @Test
    public void testCharStateRed(){
        gh.gameState = gh.charactersState;
        KeyEvent e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_D, 'D');
        keys.keyPressed(e);
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_ENTER, 'E');
        keys.keyPressed(e);
        assertEquals("red", gh.which_character);
    }


    @Test
    public void testUpPressed(){
        gh.gameState = gh.playState;
        KeyEvent e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_W, 'W');
        keys.keyPressed(e);
        assertTrue(keys.upPressed);
        keys.keyReleased(e);
        assertFalse(keys.upPressed);
    }
    @Test
    public void testDownPressed(){
        gh.gameState = gh.playState;
        KeyEvent e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_S, 'S');
        keys.keyPressed(e);
        assertTrue(keys.downPressed);
        keys.keyReleased(e);
        assertFalse(keys.downPressed);
    }
    @Test
    public void testLeftPressed(){
        gh.gameState = gh.playState;
        KeyEvent e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_A, 'A');
        keys.keyPressed(e);
        assertTrue(keys.leftPressed);
        keys.keyReleased(e);
        assertFalse(keys.leftPressed);
    }
    @Test
    public void testRightPressed(){
        gh.gameState = gh.playState;
        KeyEvent e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_D, 'D');
        keys.keyPressed(e);
        assertTrue(keys.rightPressed);
        keys.keyReleased(e);
        assertFalse(keys.rightPressed);
    }
    @Test
    public void testPausePressed(){
        gh.gameState = gh.playState;
        KeyEvent e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_P, 'P');
        keys.keyPressed(e);
        assertTrue(keys.pausePressed);
        keys.keyReleased(e);
        assertFalse(keys.pausePressed);
    }

    @Test
    public void testPause(){
        gh.gameState = gh.playState;
        KeyEvent e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_P, 'P');
        keys.keyPressed(e);
        assertEquals(gh.pauseState, gh.gameState);
    }
    @Test
    public void testPauseResumeVkP(){
        gh.gameState = gh.pauseState;
        KeyEvent e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_P, 'P');
        keys.keyPressed(e);
        assertEquals(gh.playState, gh.gameState);
    }
    @Test
    public void testPauseResumeButton(){
        gh.gameState = gh.pauseState;
        KeyEvent e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_S, 'S');
        keys.keyPressed(e);
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_S, 'S');
        keys.keyPressed(e);
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_W, 'W');
        keys.keyPressed(e);
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_S, 'S');
        keys.keyPressed(e);

        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_ENTER, 'E');
        keys.keyPressed(e);
        assertEquals(gh.playState, gh.gameState);
    }
    @Test
    public void testPauseMenuButton(){
        gh.gameState = gh.pauseState;
        KeyEvent e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_W, 'W');
        keys.keyPressed(e);
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_W, 'W');
        keys.keyPressed(e);
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_W, 'W');
        keys.keyPressed(e);

        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_ENTER, 'E');
        keys.keyPressed(e);
        assertEquals(gh.titleState, gh.gameState);
    }

    @Test
    public void testPressedAny(){
        gh.gameState = gh.playState;
        KeyEvent e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_W, 'W');
        keys.keyPressed(e);
        assertTrue(keys.pressedAny());
    }

    @Test
    public void testTitleState(){
        gh.gameState = gh.titleState;

        gh.ui.commandNum = 2;
        KeyEvent e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_W, 'W');
        keys.keyPressed(e);
        assertEquals(1, gh.ui.commandNum);
        gh.gameState = gh.titleState;

        gh.ui.commandNum = 1;
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_W, 'W');
        keys.keyPressed(e);
        assertEquals(3, gh.ui.commandNum);
        gh.gameState = gh.titleState;

        gh.ui.commandNum = 2;
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_S, 'S');
        keys.keyPressed(e);
        assertEquals(3, gh.ui.commandNum);
        gh.gameState = gh.titleState;

        gh.ui.commandNum = 3;
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_S, 'S');
        keys.keyPressed(e);
        assertEquals(1, gh.ui.commandNum);
        gh.gameState = gh.titleState;

        gh.ui.commandNum = 1;
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_ENTER, 'E');
        keys.keyPressed(e);
        assertEquals(gh.charactersState, gh.gameState);

        gh.gameState = gh.titleState;
        gh.ui.commandNum = 2;
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_ENTER, 'E');
        keys.keyPressed(e);
        assertEquals(gh.creditsState, keys.gh.gameState);

        gh.gameState = gh.titleState;
        //EXIT
        gh.ui.commandNum = 3;
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_ENTER, 'E');
        //catch exit
    }

    @Test
    public void testWinState() {
        gh.gameState = gh.winState;

        // Test scrolling up
        gh.ui.commandNum = 2;
        KeyEvent e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_W, 'W');
        keys.keyPressed(e);
        assertEquals(1, gh.ui.commandNum);

        gh.ui.commandNum = 1;
        keys.keyPressed(e);
        assertEquals(3, gh.ui.commandNum);

        // Test scrolling down
        gh.ui.commandNum = 1;
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_S, 'S');
        keys.keyPressed(e);
        assertEquals(2, gh.ui.commandNum);

        gh.ui.commandNum = 3;
        keys.keyPressed(e);
        assertEquals(1, gh.ui.commandNum);


        //Enter tests
        gh.ui.commandNum = 1;
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_ENTER, 'E');
        keys.keyPressed(e);
        assertEquals(gh.playState, gh.gameState);

        gh.gameState = gh.winState;

        gh.ui.commandNum = 2;
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_ENTER, 'E');
        keys.keyPressed(e);
        assertEquals(gh.titleState, gh.gameState);

        gh.gameState = gh.winState;


        gh.ui.commandNum = 3;
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_ENTER, 'E');
        //catch exit
    }

    @Test
    public void testLoseState() {
        gh.gameState = gh.loseState;

        // Test scrolling up
        gh.ui.commandNum = 2;
        KeyEvent e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_W, 'W');
        keys.keyPressed(e);
        assertEquals(1, gh.ui.commandNum);

        gh.ui.commandNum = 1;
        keys.keyPressed(e);
        assertEquals(3, gh.ui.commandNum);

        // Test scrolling down
        gh.ui.commandNum = 1;
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_S, 'S');
        keys.keyPressed(e);
        assertEquals(2, gh.ui.commandNum);

        gh.ui.commandNum = 3;
        keys.keyPressed(e);
        assertEquals(1, gh.ui.commandNum);

        gh.ui.commandNum = 1;
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_ENTER, 'E');
        keys.keyPressed(e);
        assertEquals(gh.playState, gh.gameState);

        gh.gameState = gh.loseState;

        gh.ui.commandNum = 2;
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_ENTER, 'E');
        keys.keyPressed(e);
        assertEquals(gh.titleState, gh.gameState);

        gh.gameState = gh.loseState;

        gh.ui.commandNum = 3;
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_ENTER, 'E');
        //catch exit
    }

    @Test
    public void testCreditsState() {
        gh.gameState = gh.creditsState;

        KeyEvent e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_W, 'W');
        keys.keyPressed(e);
        assertEquals(1, gh.ui.commandNum);

        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_S, 'S');
        keys.keyPressed(e);
        assertEquals(1, gh.ui.commandNum);

        // Test enter
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_ENTER, 'E');
        keys.keyPressed(e);
        assertEquals(gh.titleState, gh.gameState);
        assertEquals(1, gh.ui.commandNum);

        gh.gameState = gh.creditsState;

        // Test escape
        e = new KeyEvent(gh, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0,
                KeyEvent.VK_ESCAPE, 'E');
        keys.keyPressed(e);
        assertEquals(gh.titleState, gh.gameState);
        assertEquals(1, gh.ui.commandNum);
    }
}
