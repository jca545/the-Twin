package com.group5.twins_game;

import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.Assert.*;

public class MapFactoryTest {

    int screenCol = 32;
    int screenRow = 24;
    int screenHeight = screenRow*24;
    int screenWidth = screenCol*24;
    BufferedImage backgroundLayer = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D background2d = backgroundLayer.createGraphics();
    GameHandler gh = new GameHandler();

    Board board = new Board(gh.screenCol, gh.screenRow);

    MapFactory testing = new MapFactory();
    @Test
    public void wallTest() throws IOException {
        testing.makeWallCell(new Positions(1,1),board,background2d);
        assertTrue(board.cellBoard[1][1]instanceof WallCell);
    }

    @Test
    public void grassTest() throws IOException {
        testing.makeWallCell(new Positions(1,1),board,background2d);
        testing.makeGrassCell(new Positions(1,1),board,background2d);
        assertTrue(board.cellBoard[1][1]instanceof GrassCell);
    }
    @Test
    public void doorTest() throws IOException {
        testing.makeDoorCell(new Positions(1,1),board,background2d);
        assertTrue(board.cellBoard[1][1]instanceof DoorCell);
    }
    @Test
    public void goalTest() throws IOException {
        KeyControl key = new KeyControl(gh, background2d);
        Player p = new Player(key, "red",background2d);
        testing.makeGoalCell(new Positions(1,1),board,background2d,p);
        assertTrue(board.cellBoard[1][1]instanceof GoalCell);
    }


}
