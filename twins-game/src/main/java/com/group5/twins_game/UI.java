package com.group5.twins_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * A class for all UI designs
 *
 * @author Jordan Clough
 * @author Winnie Chan
 *
 * @version 1.0
 * @see GameHandler
 * @see KeyControl
 */
public class UI {
    GameHandler gh;
    Graphics2D g2;
    Font avenir20, avenir30, avenir60B, avenir80B;
    BufferedImage keyImage;
    Player imgPlayer;
    GoalCell imGoalCell;

    public int commandNum = 0;
    String text;

    /**
     * The constructor for UI class, to set up
     * default fonts and load images needed for display
     *
     * @param gh The GameHandler of this game
     *
     * @author Jordan Clough
     */
    public UI(GameHandler gh){
        this.gh = gh;
        this.imgPlayer = new Player(null, "red", g2);
        this.imGoalCell = new GoalCell(null, imgPlayer);

        avenir20 = new Font("Avenir", Font.PLAIN, 20);
        avenir30 = new Font("Avenir", Font.PLAIN, 30);
        avenir60B = new Font("Avenir", Font.BOLD, 60);
        avenir80B = new Font("Avenir Black", Font.BOLD, 80);

        //Key
        try {
            Key key = new Key();
            keyImage = key.img;
        } catch (IOException e) {
            // Handle the exception
            System.out.println(e.getClass() + " is catching error");
        }
    }

    /**
     * The function that determines which Screen to draw
     *
     * @param g2 used to draw out onto the game window
     *
     * @author Jordan Clough
     * @author Winnie Chan
     */
    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(avenir20);
        g2.setColor(Color.white);

        if(gh.gameState == gh.loseState){
            drawLoseScreen();}
        if(gh.gameState == gh.titleState){
            drawTitleScreen();}
        if(gh.gameState == gh.winState){
            drawWinScreen();}
        if(gh.gameState == gh.playState){
            drawPlayScreen();}
        if(gh.gameState == gh.pauseState){
            drawPauseScreen();}
        if(gh.gameState == gh.creditsState){
            drawCreditsScreen();}
        if(gh.gameState == gh.charactersState){
            drawCharactersScreen();}
    }

    /**
     * Function that draws the Credit Screen
     *
     * @author Jordan Clough
     */
    public void drawCreditsScreen(){
        g2.setColor(new Color(29, 148, 52));
        g2.fillRect(0, 0, gh.screenWidth, gh.screenHeight);
        g2.setFont(avenir60B);
        text = "Credits";
        int x = getCenterTextX(text);
        int y = gh.screenHeight  / 5;
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        g2.setFont(avenir20);

        //PlayGame
        String[] texts = new String[]{"Winnie Chan","Jordan Clough",
                "Jin Yang", "Joseph Zhang"};
        for (int i = 4; i < 8; i++){
            text = texts[i-4];
            y = gh.screenHeight * i / 10;
            x = getCenterTextX(text);
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
        }

        //PlayGame
        y = gh.screenHeight * 9 / 10;
        text = "BACK TO MENU";
        drawButtons(text, commandNum == 1, y);
    }

    /**
     * Function that draws the Title Screen
     *
     * @author Jordan Clough
     * @author Winnie Chan
     */
    public void drawTitleScreen(){
        //background
        g2.setColor(new Color(29, 148, 52));
        g2.fillRect(0, 0, gh.screenWidth, gh.screenHeight);

        //GameName
        g2.setFont(avenir80B);
        String text = "The Twins";
        int x = getCenterTextX(text);
        int y = gh.screenHeight / 4;

        //GameNameShadow
        g2.setColor(new Color(38, 38, 38));
        g2.drawString(text, x+6, y+6);
        //GameNameText
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        //Player tiles
        //left(player)
        x = gh.screenWidth/8 - 60;
        y = gh.screenHeight/2 - 55;
        g2.drawImage(this.imgPlayer.dImg.downImg, x, y, 120, 120, null);

        //right(goal)
        x = gh.screenWidth*7/8 - 60;
        g2.drawImage(this.imGoalCell.img, x, y, 120, 120, null);

        //keys
        try {
            Key imgKey = new Key();
            //Left Key
            x = gh.screenWidth/8 - 60;
            y = gh.screenHeight*7/8 - 60;
            g2.drawImage(imgKey.img, x, y, 120, 120, null);
            
            //Right key
            x = gh.screenWidth*7/8 - 60;
            y = gh.screenHeight*7/8 - 60;
            g2.drawImage(imgKey.img, x, y, 120, 120, null);
        } catch (IOException e) {
            // Handle the exception
            System.out.println(e.getClass() + " is catching error");
        }

        //menu font
        g2.setFont(avenir30);

        // Title's PlayGame
        text = "PLAY GAME";
        y = gh.screenHeight * 4/9;
        drawButtons(text, commandNum == 1, y);

        // Title's Credits
        text = "CREDITS";
        y = gh.screenHeight * 6/9;
        drawButtons(text, commandNum == 2, y);

        // Title's Quit
        text = "QUIT GAME";
        y = gh.screenHeight * 8/9;
        drawButtons(text, commandNum == 3, y);

    }

    /**
     * Function that draws the Character Screen
     *
     * @author Jordan Clough
     * @author Winnie Chan
     */
    public void drawCharactersScreen() {
        g2.setColor(new Color(29, 148, 52));
        g2.fillRect(0, 0, gh.screenWidth, gh.screenHeight);

        // Character's state
        g2.setFont(avenir60B);
        String text = "Choose Character";
        int x = getCenterTextX(text);
        int y = gh.screenHeight / 4;

        // Shadow's color
        g2.setColor(new Color(146, 191, 214));
        g2.drawString(text, x+3, y+3);
        // Text: Choose
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);


        // Buttons
        g2.setFont(avenir30);
        int rectx, recty;
        int rectHeight = gh.screenHeight/8;
        int rectWidth = gh.screenWidth * 2/7;
        int imgSize = 240;

        // Buttons
        text = "Choose";
        //set y and x string
        int midX = gh.screenWidth/2;
        int gap = gh.screenWidth/15;

        // Character's left button (Blue)
        rectx = midX - gap - rectWidth;
        recty = gh.screenHeight * 7 / 9;

        int rect_text = rectx / 2;
        // Text position (x)
        x = rectx  + rect_text;
        y = recty * 11 / 10;

        // blue's img
        int imgX = rectx - 20;
        int imgY = (gh.screenHeight / 4) + 45;
        if (gh.which_character.equals("blue")){
            g2.drawImage(gh.player.dImg.downImg, imgX, imgY, imgSize, imgSize, null);
        } else {
            g2.drawImage(gh.player.sisImg, imgX, imgY, imgSize, imgSize, null);
        }
        if (commandNum == 4){
            g2.setColor(new Color(172, 207, 242));

        } else {
            text = "";
            g2.setColor(new Color(79,79,79));

        }
        g2.fillRect(rectx, recty, rectWidth, rectHeight);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // Character's right button (Red)
        text = "Choose";
        rectx = midX + gap;
        x = rectx  + rect_text;

        // right's img
        imgX = rectx - 20;
        imgY = (gh.screenHeight / 4) + 45;
        if (gh.which_character.equals("red")){
            g2.drawImage(gh.player.dImg.downImg, imgX, imgY, imgSize, imgSize, null);
        } else {
            g2.drawImage(gh.player.sisImg, imgX, imgY, imgSize, imgSize, null);
        }

        if (commandNum == 5){
            g2.setColor(new Color(242, 172, 180));

        } else {
            text = "";
            g2.setColor(new Color(79,79,79));

        }
        g2.fillRect(rectx, recty, rectWidth, rectHeight);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
    }

    /**
     * Function that draws the small texts under State
     *
     * @author Jordan Clough
     * @author Winnie Chan
     */
    public void drawSmallText(String what, int y){
        String text = switch (what) {
            case "score" -> "Score: " + gh.player.score.getPoints();
            case "ghost" -> "Stay away from the ghosts!";
            case "trap" -> "you lose all your coins.";
            default -> "";
        };
        g2.setFont(avenir20);
        y += 30;
        g2.drawString(text, getCenterTextX(text), y);
        text = "Time: " + gh.clock + " s";
        y += 30;
        g2.drawString(text, getCenterTextX(text), y);
    }

    /**
     * Function that draws the Losing Screen
     *
     * @author Jordan Clough
     * @author Winnie Chan
     */
    public void drawLoseScreen(){
        //LoseText
        g2.setFont(avenir60B);
        String text = "GAME OVER";
        int x = getCenterTextX(text);
        int y = gh.screenHeight/ 5;
        g2.drawString(text, x, y);

        // reason of losing
        if (gh.enemyCol.caught){
            drawSmallText("ghost", y);
        } else {
            drawSmallText("trap", y);
        }
        // Buttons
        winLoseButtons();
    }

    /**
     * Function that draws the Pausing Screen
     *
     * @author Jordan Clough
     * @author Winnie Chan
     */
    public void drawPauseScreen(){
        //Paused Text
        g2.setFont(avenir60B);
        String text = "PAUSED";
        int x = getCenterTextX(text);
        int y = gh.screenHeight/ 3;
        g2.drawString(text, x, y);
        // Score
        drawSmallText("score", y);

        g2.setFont(avenir30);
        //PlayGame

        // Pause's resume
        text = "RESUME GAME";
        y = gh.screenHeight * 7 / 10;
        drawButtons(text, commandNum == 1, y);

        // Pause's Back to menu
        text = "BACK TO MENU";
        y = gh.screenHeight * 9 / 10;
        drawButtons(text, commandNum == 2, y);
    }

    /**
     * Function that draws the Playing Screen
     *
     * @author Jordan Clough
     * @author Winnie Chan
     */
    public void drawPlayScreen(){
        String text;
        g2.setFont(avenir20);
        //keys display
        for(int i = 0; i < gh.player.score.getKeysCollected(); i++){
            g2.drawImage(keyImage, (gh.originalSpriteSize*3)*(i), gh.screenHeight-(int)(gh.originalSpriteSize*2.3), gh.originalSpriteSize*3, gh.originalSpriteSize*3, null);
        }

        // Display Score
        text = "Score: " + gh.player.score.getPoints();
        g2.drawString(text, getCenterTextX(text),
                gh.screenHeight-(gh.originalSpriteSize)/3);

        // Display Time
        text = "Time: " + gh.clock + " s";
        g2.drawString(text, (gh.screenWidth*5/6), 19);

        // Display Collected/ total number of keys
        text = "Keys collected: " + gh.player.score.getKeysCollected() + "/" + gh.builder.getKeyTotal();
        g2.setColor(new Color(247, 255, 110));
        g2.drawString(text, getCenterTextX(text), 19);
    }

    /**
     * Function that draws the Winning Screen
     *
     * @author Jordan Clough
     * @author Winnie Chan
     */
    public void drawWinScreen(){
        //LoseText
        g2.setFont(avenir60B);
        String text = "YOU WON!";
        int x = getCenterTextX(text);
        int y = gh.screenHeight/ 5;
        g2.drawString(text, x, y);

        drawSmallText("score", y);

        // buttons
       winLoseButtons();
    }

    /**
     * Function that finds horizontally the center of given text
     *
     * @param text the String that wants to find its center
     * @return the location that can make the text center in Width
     *
     * @author Jordan Clough
     */
    public int getCenterTextX(String text){
        int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gh.screenWidth/2 - textLength/2;
    }

    /**
     * Function to find the height of given text
     *
     * @param text String that wants to find the height for
     * @return the height of the test in pixels
     *
     * @author Jordan Clough
     */
    public int getTextHeight(String text){
        return (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight();
    }

    public void drawButtons(String text, boolean select, int y){
        int x = getCenterTextX(text);
        int rectHeight = gh.screenHeight /8;
        int rectWidth = gh.screenWidth /2;
        int rectx, recty;
        if(select){
            g2.setColor(new Color(159,159,159));

        }else{
            g2.setColor(new Color(79,79,79));
        }
        rectx = gh.screenWidth/4;
        recty = y - getTextHeight(text)*2/7 - rectHeight/2;
        g2.fillRect(rectx, recty, rectWidth, rectHeight);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
    }

    public void winLoseButtons(){
        int y;
        // buttons
        g2.setFont(avenir30);
        text = "PLAY AGAIN";
        y = gh.screenHeight * 5/10;
        drawButtons(text, commandNum == 1, y);

        // Win's Credits
        text = "BACK TO MENU";
        y = gh.screenHeight * 7/10;
        drawButtons(text, commandNum == 2, y);

        // Win's Quit Game
        text = "QUIT GAME";
        y = gh.screenHeight * 9/10;
        drawButtons(text, commandNum == 3, y);
    }
}


