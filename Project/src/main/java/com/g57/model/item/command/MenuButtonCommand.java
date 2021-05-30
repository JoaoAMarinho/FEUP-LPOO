package com.g57.model.item.command;

import com.g57.state.GameState;

public class MenuButtonCommand implements Command{
    private final GameState nextState;

    public MenuButtonCommand(GameState nextState) {
        this.nextState = nextState;
    }

    @Override
    public boolean execute() {
        if (nextState!=null)
            nextState.changeState(nextState);
        return true;
    }

    @Override
    public void undo() {
        nextState.changeState(nextState);
    }

    public GameState getNextState() {
        return nextState;
    }
}
