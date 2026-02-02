package com.group5.twins_game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Responds to key events and update the gameState
 *
 * @author Jin Yang
 * @author Jordan Clough
 * @author Winnie Chan
 *
 * @version 1.0
 * @see GameHandler
 * @see UI
 */
public class KeyControl implements KeyListener {
    GameHandler gh;
    Graphics2D bg2D;
    public boolean upPressed, downPressed, leftPressed, rightPressed, pausePressed;

    /**
     * Constructor of KeyControl class that takes in
     * the GameHandler class and our graph
     * @param gh the GameHandler class of the game
     * @param bg2D used to draw out onto the game window
     *
     * @author Jordan Clough
     * @author Winnie Chan
     */
    public KeyControl(GameHandler gh, Graphics2D bg2D){
        this.gh = gh;
        this.bg2D = bg2D;
    }

    /**
     * unused function
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Function that detect whether a key is pressed
     * and change the corresponding boolean to true.
     * Also change the gameState based on different situations
     *
     * @param e the event to be processed
     *
     * @author Jordan Clough
     * @author Winnie Chan
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        // Characters state
        if (gh.gameState == gh.charactersState){
            if(key == KeyEvent.VK_A) {
                gh.ui.commandNum = 4;
            }
            if(key == KeyEvent.VK_D) {
                gh.ui.commandNum = 5;
            }
            if(key == KeyEvent.VK_ENTER){
                if (gh.ui.commandNum == 4){
                    gh.which_character = "blue";
                } else if (gh.ui.commandNum == 5){
                    gh.which_character = "red";
                }
                gh.player = new Player(this, gh.which_character,  bg2D);
                gh.gameState = gh.playState;
                gh.resetGame();
            }
        }
        // Play state
        if(gh.gameState == gh.playState){
            if(key == KeyEvent.VK_W) {
                upPressed = true;
            }
            if(key == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if(key == KeyEvent.VK_S) {
                downPressed = true;
            }
            if(key == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if(key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_P){
                pausePressed = true;
                gh.gameState = gh.pauseState;
                gh.ui.commandNum = 1;
            }
        // Pause -> Play
        } else if(gh.gameState == gh.pauseState){
            if(key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_P){
                gh.gameState = gh.playState;
            }
        }
        // TitleState
        if(gh.gameState == gh.titleState){
            // scroll up and down
            if(key == KeyEvent.VK_W) {
                if(gh.ui.commandNum > 1){
                    gh.ui.commandNum--;
                }else if(gh.ui.commandNum == 1){
                    gh.ui.commandNum = 3;
                }/*else if(gh.ui.commandNum < 1 || gh.ui.commandNum > 3){
                    gh.ui.commandNum = 1;
                }*/
            }
            if(key == KeyEvent.VK_S) {
                if(gh.ui.commandNum < 3){
                    gh.ui.commandNum++;
                }else if(gh.ui.commandNum == 3){
                    gh.ui.commandNum = 1;
                }/*else if(gh.ui.commandNum < 1 || gh.ui.commandNum > 3){
                    gh.ui.commandNum = 1;
                }*/
            }
            // Enter Pressed
            if(key == KeyEvent.VK_ENTER){
                // TITLE = 1
                // Title -> Start Game
                if(gh.ui.commandNum == 1){
                    gh.gameState = gh.charactersState;

                // Title -> Credit
                }else if(gh.ui.commandNum == 2){
                    gh.gameState = gh.creditsState;
                // Title -> Quit
                }else if(gh.ui.commandNum == 3){
                    System.exit(0);
                }
            }
        }
        // WinState
        if(gh.gameState == gh.winState){
            //scroll up and down
            if(key == KeyEvent.VK_W) {
                if(gh.ui.commandNum > 1){
                    gh.ui.commandNum--;
                }else if(gh.ui.commandNum == 1){
                    gh.ui.commandNum = 3;
                }/*else if(gh.ui.commandNum < 1 || gh.ui.commandNum > 3){
                    gh.ui.commandNum = 1;
                }*/
            }
            if(key == KeyEvent.VK_S) {
                if(gh.ui.commandNum < 3){
                    gh.ui.commandNum++;
                }else if(gh.ui.commandNum == 3){
                    gh.ui.commandNum = 1;
                }/*else if(gh.ui.commandNum < 1 || gh.ui.commandNum > 3){
                    gh.ui.commandNum = 1;
                }*/
            }
            //Enter Pressed
            if(key == KeyEvent.VK_ENTER){
                //TITLE = 1
                // Win -> Restart
                if(gh.ui.commandNum == 1){
                    gh.resetGame();
                    gh.gameState = gh.playState;
                // Win -> Credit
                }else if(gh.ui.commandNum == 2){
                    //credits TO DO
                    gh.resetGame();
                    gh.gameState = gh.titleState;
                    gh.ui.commandNum = 1;
                // Win -> Quit
                }else if(gh.ui.commandNum == 3){
                    System.exit(0);
                }
            }
        }
        // LoseState
        if(gh.gameState == gh.loseState){
            //scroll up and down
            if(key == KeyEvent.VK_W) {
                if(gh.ui.commandNum > 1){
                    gh.ui.commandNum--;
                }else if(gh.ui.commandNum == 1){
                    gh.ui.commandNum = 3;
                }/*else if(gh.ui.commandNum < 1 || gh.ui.commandNum > 3){
                    gh.ui.commandNum = 1;
                }*/
            }
            if(key == KeyEvent.VK_S) {
                if(gh.ui.commandNum < 3){
                    gh.ui.commandNum++;
                }else if(gh.ui.commandNum == 3){
                    gh.ui.commandNum = 1;
                }/*else if(gh.ui.commandNum < 1 || gh.ui.commandNum > 3){
                    gh.ui.commandNum = 1;
                }*/
            }
            //Enter Pressed
            if(key == KeyEvent.VK_ENTER){
                // Lose -> restart
                if(gh.ui.commandNum == 1){
                    //Restart game
                    gh.resetGame();
                    gh.gameState = gh.playState;
                // Lose -> title
                }else if(gh.ui.commandNum == 2){
                    gh.resetGame();
                    gh.gameState = gh.titleState;
                    gh.ui.commandNum = 1;
                // Lose -> quit
                }else if(gh.ui.commandNum == 3){
                    System.exit(0);
                }
            }
        }
        // CreditState
        if(gh.gameState == gh.creditsState){
            if(key == KeyEvent.VK_W) {
                gh.ui.commandNum = 1;
            }
            if(key == KeyEvent.VK_S) {
                gh.ui.commandNum = 1;
            }
            if((gh.ui.commandNum == 1 && key == KeyEvent.VK_ENTER) || key == KeyEvent.VK_ESCAPE){
                gh.gameState = gh.titleState;
                gh.ui.commandNum = 1;
            }
        }

        /*// Play state
        if(gh.gameState == gh.playState){
            if(key == KeyEvent.VK_W) {
                upPressed = true;
            }
            if(key == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if(key == KeyEvent.VK_S) {
                downPressed = true;
            }
            if(key == KeyEvent.VK_D) {
                rightPressed = true;
            }
        }*/

        //Pause
        if(gh.gameState == gh.pauseState){
            if(key == KeyEvent.VK_W) {
                if(gh.ui.commandNum > 1){
                    gh.ui.commandNum--;
                }else{
                    gh.ui.commandNum = 2;
                }
            }
            if(key == KeyEvent.VK_S) {
                if(gh.ui.commandNum < 2){
                    gh.ui.commandNum++;
                }else {
                    gh.ui.commandNum = 1;
                }
            }
            /*// Pause -> Resume
            if(key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_P){
                if(!pausePressed){
                    pausePressed = true;
                    gh.gameState = gh.playState;
                }
            }*/
            if(key == KeyEvent.VK_ENTER){
                // Pause -> Resume
                if(gh.ui.commandNum == 1){
                    //Resume
                    gh.gameState = gh.playState;
                // Pause -> Back to menu
                }else if(gh.ui.commandNum == 2){
                    //back to menu
                    gh.resetGame();
                    gh.gameState = gh.titleState;
                    gh.ui.commandNum = 1;
                }
            }
        }
    }

    /**
     * Function that detect if a key is released
     * and turn the corresponding boolean to false
     *
     * @param e the event to be processed
     *
     * @author Jordan Clough
     * @author Winnie Chan
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(key == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(key == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(key == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if(key == KeyEvent.VK_P || key == KeyEvent.VK_ESCAPE){
            pausePressed = false;
        }
    }

    /**
     * Function to check if any direction key is pressed
     *
     * @return True is either one of the direction key is pressed,
     * False otherwise
     *
     * @author Winnie Chan
     */
    public boolean pressedAny(){
        return (upPressed || downPressed ||
                leftPressed || rightPressed);
    }
}
