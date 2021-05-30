package com.g57.controller;

import com.g57.gui.GUI;
import com.g57.model.Position;
import com.g57.model.element.button.Button;
import com.g57.model.item.command.MenuButtonCommand;
import com.g57.state.GameState;
import com.g57.state.listener.KeyBoardListener;
import com.g57.state.listener.MouseListener;
import com.g57.viewer.state.MenuViewer;
import com.g57.viewer.state.StateViewer;

import java.io.IOException;

public class MenuController implements MouseListener, KeyBoardListener {
    private final GameState gameState;
    private final StateViewer menuViewer;

    public MenuController(GameState gameState, GUI gui){
        this.gameState = gameState;
        this.menuViewer = new MenuViewer(gui, gameState.getButtons());
    }

    public void step() throws IOException {
        menuViewer.draw();
    }

    private void changeState(Button button) {
        MenuButtonCommand buttonCommand = (MenuButtonCommand) button.getCommand();
        if (buttonCommand.getNextState()==null){
            gameState.changeState(null);
            return;
        }
        buttonCommand.execute();
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
