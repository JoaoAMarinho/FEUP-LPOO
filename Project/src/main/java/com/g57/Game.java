package com.g57;

import com.g57.gui.GUI;
import com.g57.gui.KeyBoardObserver;
import com.g57.gui.LanternaGUI;
import com.g57.gui.MouseObserver;
import com.g57.state.GameState;
import com.g57.state.MenuState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final int width;
    private final int height;
    private final int fps;
    private final GUI gui;
    private GameState gameState;
    private final MouseObserver mouseObserver;
    private final KeyBoardObserver keyBoardObserver;

    public Game(int width, int height, int fps) throws IOException, URISyntaxException, FontFormatException {
        this.width = width;
        this.height = height;
        this.fps = fps;
        this.gui = new LanternaGUI(width,height);
        this.mouseObserver = new MouseObserver();
        this.keyBoardObserver = new KeyBoardObserver();
        this.gameState = new MenuState(this, gui);
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException {
        Game game = new Game(25, 25, 30);
        game.start();
    }

    public void start() throws IOException {
        int frameTime = 1000 / this.fps;

        gui.addMouseListener(mouseObserver);
        gui.addKeyBoardListener(keyBoardObserver);
        this.gameState.start();

        while(gameState!=null && gui.isActive()){
            long startTime = System.currentTimeMillis();

            gameState.step(this, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            if (sleepTime > 0) try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {

            }
        }

        gui.close();
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        if(gameState!=null)
            this.gameState.start();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public MouseObserver getMouseObserver() {
        return mouseObserver;
    }

    public KeyBoardObserver getKeyBoardObserver() {
        return keyBoardObserver;
    }
}
