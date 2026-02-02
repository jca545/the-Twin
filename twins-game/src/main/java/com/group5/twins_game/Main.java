package com.group5.twins_game;

import javax.swing.*;

/**
 * Creates window width specified dimensions and settings.
 *
 * @author Jin Yang
 *
 */
public class Main {
    /**
     * Main function to run this game
     *
     * @author Jin Yang
     */
    public static void main( String[] args ) {
        JFrame gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setTitle("The Twins");

        GameHandler screen = new GameHandler();
        gameWindow.add(screen);
        gameWindow.pack();

        gameWindow.setVisible(true);
        screen.createGameLoop();
    }
}