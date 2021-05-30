package com.g57.state;

import com.g57.Game;
import com.g57.model.element.button.Button;

import java.io.IOException;
import java.util.List;

public abstract class GameState {
    protected final Game game;
    private final List<Button> buttons;

    public GameState(Game game, List<Button> buttons) {
        this.game = game;
        this.buttons = buttons;
    }

    public abstract void start();
    public abstract void step(Game game, long time) throws IOException;
    public void changeState(GameState gameState){
        this.game.setGameState(gameState);
    };

    public Game getGame(){
        return game;
    }
    public List<Button> getButtons(){
        return buttons;
    }
}
