package com.g57.controller;

import com.g57.gui.GUI;
import com.g57.model.Position;
import com.g57.model.element.button.Button;
import com.g57.state.GameState;
import com.g57.state.listener.KeyBoardListener;
import com.g57.state.listener.MouseListener;
import com.g57.viewer.state.PauseViewer;
import com.g57.viewer.state.StateViewer;

import java.io.IOException;

public class PauseController implements KeyBoardListener, MouseListener {
    private final GameState gameState;
    private final StateViewer pauseViewer;

    public PauseController(GameState gameState, GUI gui){
        this.gameState = gameState;
        this.pauseViewer = new PauseViewer(gui, gameState.getButtons());
    }

    public void step() throws IOException {
        pauseViewer.draw();
    }

    @Override
    public void click(Position position) {
        Position clickedPos = getRealPosition(position,gameState.getGame().getWidth(), gameState.getGame().getHeight());
        for (Button button:gameState.getButtons()){
            Position buttonPos = button.getPosition();
            if (buttonPosition(clickedPos, buttonPos, button.getWidth(), button.getHeight())){
                button.getCommand().execute();
            }
        }
    }

    @Override
    public void move(Position position) {
        Position currentPosition = getRealPosition(position,gameState.getGame().getWidth(), gameState.getGame().getHeight());
        for(Button button: gameState.getButtons()) {
            Position buttonPos = button.getPosition();
            if(buttonPosition(currentPosition, buttonPos, button.getWidth(), button.getHeight())) {
                button.highlight();
                continue;
            }
            if(button.isHighlighted())
                button.toneDown();
        }
    }

    @Override
    public void keyPressed(GUI.ACTION action) {
        if(action == GUI.ACTION.QUIT){
            gameState.changeState(null);
        }
    }
}
