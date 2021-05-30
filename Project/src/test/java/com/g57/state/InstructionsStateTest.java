package com.g57.state;

import com.g57.Game;
import com.g57.controller.InstructionsController;
import com.g57.gui.GUI;
import com.g57.gui.KeyBoardObserver;
import com.g57.gui.LanternaGUI;
import com.g57.gui.MouseObserver;
import com.g57.model.element.button.Button;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstructionsStateTest {
    private InstructionsState gameState;
    private Game game;
    private GUI gui;
    private InstructionsController instructionsController;
    private MouseObserver mouseObserver;
    private KeyBoardObserver keyBoardObserver;

    @BeforeEach
    void setUp() throws IOException {
        this.game = Mockito.mock(Game.class);
        this.gui = Mockito.mock(LanternaGUI.class);
        this.mouseObserver = Mockito.mock(MouseObserver.class);
        this.keyBoardObserver = Mockito.mock(KeyBoardObserver.class);

        this.gameState = new InstructionsState(game, gui);
        this.instructionsController = Mockito.mock(InstructionsController.class);
        this.gameState.setInstructionsController(instructionsController);

        Mockito.when(game.getMouseObserver()).thenReturn(mouseObserver);
        Mockito.when(game.getKeyBoardObserver()).thenReturn(keyBoardObserver);
    }


    @Test
    void testStart() {
        gameState.start();

        Mockito.verify(mouseObserver, Mockito.times(1)).setListener(instructionsController);
        Mockito.verify(keyBoardObserver, Mockito.times(1)).setListener(instructionsController);
    }

    @Test
    void testStep() throws IOException {
        gameState.step(game, 10);

        Mockito.verify(instructionsController, Mockito.times(1)).step();
    }

    @Test
    void testGetInstructionsController() {
        InstructionsController newInstructionsController = gameState.getInstructionsController();

        Assertions.assertEquals(newInstructionsController, instructionsController);
    }

    @Test
    void testSetInstructionsController() {
        InstructionsController newInstructionsController = new InstructionsController(gameState, gui);
        gameState.setInstructionsController(newInstructionsController);

        Assertions.assertEquals(gameState.getInstructionsController(), newInstructionsController);
    }
}