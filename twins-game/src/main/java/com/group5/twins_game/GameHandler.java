package com.group5.twins_game;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


import static java.lang.Thread.sleep;

/**
 * Controls the size of the screen and the Thread. The Thread updates
 * and draws game components at a specified rate.
 *
 * @author Jin Yang
 * @author Jordan Clough
 * @author Joseph Zhang
 * @author Winnie Chan
 *
 * @version 1.0
 */
public class GameHandler extends JPanel implements Runnable{
    int originalSpriteSize = 24;
    int scale = 1;
    public int cellSize = originalSpriteSize*scale;
    int screenCol = 32;
    int screenRow = 24;
    int screenHeight = screenRow*cellSize;
    int screenWidth = screenCol*cellSize;
    public Board cellBoard;

    int fps = 60;
    int frame = 0;  // for counting a second
    int clock = 0;  // for display time
    int waitTime = (1000/fps);
    boolean runningGame = true;
    Thread gameLoop;

    BufferedImage backgroundLayer = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D background2d = backgroundLayer.createGraphics();
    KeyControl keys = new KeyControl(this, background2d);

    MapFactory mfactory;
    ItemFactory ifactory;
    MapBuilder builder;
    public String which_character = "blue";
    Player player;
    EnemyCollection enemyCol;

    public UI ui = new UI(this);

    //Game State
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int titleState = 3;
    public final int winState = 4;
    public final int loseState = 5;
    public final int creditsState = 6;
    public final int charactersState = 7;



    /**
     * The constructor of GameHandler that set up the game
     *
     * @author Jin Yang
     * @author Jordan Clough
     * @author Winnie Chan
     */
    public GameHandler() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.addKeyListener(keys);
        this.setFocusable(true);
        this.setupGame();
    }

    /**
     * Set the initial gameState to titleState
     *
     * @author Jordan Clough
     * @author Winnie Chan
     */
    public void setupGame() {
        gameState = titleState;
        ui.commandNum = 1;
    }

    /**
     * finds the max index of either height or width
     *
     * @param xy String of "x" for Width, "y" for Height
     * @return the max index of the map matrix[][]
     *
     * @author Winnie Chan
     */
    public int getMaxIndex(String xy){
        if (Objects.equals(xy, "x")){
            return screenCol-1;
        } else if (Objects.equals(xy, "y")){
            return screenRow-1;
        } else {
            System.out.println("wrong input of getting max x, y index");
            return -1;
        }
    }

    /**
     * Creates a Thread object that is used to update
     * game components every "tick" of the game.
     *
     * @author Jin Yang
     */
    public void createGameLoop() {
        gameLoop = new Thread(this);
        gameLoop.start();
    }


    /**
     * Overrides abstract function from the Runnable interface.
     * Used to update and redraw game components every "tick" of
     * the game.
     *
     * @author Jin Yang
     * @author Jordan Clough
     */
    public void run() {
        long initTime;
        long elapsedTime;
        long remainingTime;

        resetGame();
        while (runningGame && this.player != null) {
            initTime = System.currentTimeMillis();
            try {
                update();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            repaint();
            elapsedTime = System.currentTimeMillis() - initTime;

            remainingTime = ( waitTime - elapsedTime);
            if (remainingTime < 0) {
                remainingTime = 0;
            }

            try {
                sleep(remainingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * Updates states of all components in the game
     * including the player, enemy, trap/reward, score,
     * keyCollected, etc.
     *
     * @author Jin Yang
     * @author Jordan Clough
     * @author Winnie Chan
     */
    public void update() throws IOException {

        if(gameState == playState && this.player != null && this.cellBoard != null){
            frame++;
            player.update(cellBoard);
            if(player.score.getKeysCollected() == builder.getKeyTotal()){
                cellBoard.door.unlockDoor();
                if(player.isAtGoal(cellBoard.gCell)){
                    gameState = winState;
                }
            }
            enemyCol.updateIter(player.position, cellBoard);
            if (enemyCol.caught || player.score.getPoints() < 0) {
                gameState = loseState;
            }
            if (frame == 60){
                frame = 0;
                clock++;
            }
        }
    }

    /**
     * Resets the Entire game including
     * map, player, enemies, score and items,
     *
     * @author Jordan Clough
     * @author Winnie Chan
     */
    public void resetGame(){
        frame = 0;
        clock = 0;
        this.player = new Player(keys, which_character, background2d);
        this.cellBoard = new Board(screenCol, screenRow);

        this.builder = new MapBuilder(this);
        this.mfactory = new MapFactory();
        this.ifactory = new ItemFactory();
        this.cellBoard.insert(player, player.position);

        try {
            builder.buildMap(mfactory, ifactory, cellBoard, background2d, player);
            Positions[] posArr = {new Positions(3,3), new Positions(15,15), new Positions(13, 5)};
            enemyCol = builder.setEnemies(posArr,3,cellBoard);
            //enemyCol.checkEnemy();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /** Draws images of all components that are visible on screen
     * including map, player, enemy, items, etc
     *
     * @param images the <code>Graphics</code> object to protect
     *
     * @author Jin Yang
     * @author Jordan Clough
     * @author Winnie Chan
     */
        public void paintComponent(Graphics images) {
            super.paintComponent(images);
            Graphics2D image2D = (Graphics2D)images;
            //Title
            if(gameState == titleState || gameState == creditsState){
                ui.draw(image2D);
            } else{
                images.drawImage(backgroundLayer, 0, 0, this);
                player.draw(image2D);
                enemyCol.drawIter(image2D);
                ui.draw(image2D);
            }
        }
    }



