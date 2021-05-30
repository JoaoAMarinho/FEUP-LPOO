package com.g57.model.item.command;

import com.g57.model.element.button.Button;
import com.g57.state.GameState;
import com.g57.state.PauseState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class MenuButtonCommandTest {
    MenuButtonCommand menuButtonCommand;
    GameState gameState;

    @BeforeEach
    void setUp() {
        gameState = Mockito.mock(PauseState.class);
        menuButtonCommand = new MenuButtonCommand(gameState);

    }

    @Test
    void execute() {
        menuButtonCommand.execute();
        Mockito.verify(gameState, Mockito.times(1)).changeState(gameState);

        MenuButtonCommand menuButtonCommandnull = new MenuButtonCommand(null);

        Assertions.assertTrue(menuButtonCommandnull.execute());

    }

    @Test
    void undo() {

        menuButtonCommand.undo();
        Mockito.verify(gameState, Mockito.times(1)).changeState(gameState);
    }
}