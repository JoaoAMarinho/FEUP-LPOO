package com.g57.controller;

import com.g57.Game;
import com.g57.gui.GUI;
import com.g57.gui.LanternaGUI;
import com.g57.model.element.button.Button;
import com.g57.model.item.command.Command;
import com.g57.state.InstructionsState;
import com.g57.state.PlayingState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstructionsControllerTest {
    InstructionsController instructionsController;
    InstructionsState instructionsState;
    @BeforeEach
    void setUp() throws IOException {
        Game game = Mockito.mock(Game.class);
        Mockito.when(game.getHeight()).thenReturn(1);
        Mockito.when(game.getWidth()).thenReturn(1);
        GUI gui = Mockito.mock(LanternaGUI.class);
        this.instructionsState = new InstructionsState(game, gui);
        this.instructionsController = new InstructionsController(instructionsState,gui);
    }
    @Test
    void click() {
        List<Button> buttons = instructionsState.getButtons();
        Button button = buttons.get(buttons.size()-1);
        Command command = Mockito.mock(Command.class);
        button.setCommand(command);

        instructionsController.click(button.getPosition());

        Mockito.verify(command, Mockito.times(1)).execute();
    }

    @Test
    void move() {
        List<Button> buttons = instructionsState.getButtons();
        Button button = buttons.get(buttons.size() - 1);
        instructionsController.move(button.getPosition());
        Assertions.assertTrue(button.isHighlighted());
    }
}