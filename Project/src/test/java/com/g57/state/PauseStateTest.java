package com.g57.state;

import com.g57.Game;
import com.g57.controller.PauseController;
import com.g57.gui.GUI;
import com.g57.gui.KeyBoardObserver;
import com.g57.gui.LanternaGUI;
import com.g57.gui.MouseObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

class PauseStateTest {
    private PauseState gameState;
    private Game game;
    private GUI gui;
    private PauseController pauseController;
    private MouseObserver mouseObserver;
    private KeyBoardObserver keyBoardObserver;

    @BeforeEach
    void setUp() throws IOException {
        this.game = Mockito.mock(Game.class);
        this.gui = Mockito.mock(LanternaGUI.class);
        this.mouseObserver = Mockito.mock(MouseObserver.class);
        this.keyBoardObserver = Mockito.mock(KeyBoardObserver.class);

        this.gameState = new PauseState(game, gui, Mockito.mock((GameState.class)));
        this.pauseController = Mockito.mock(PauseController.class);
        this.gameState.setPauseController(pauseController);

        Mockito.when(game.getMouseObserver()).thenReturn(mouseObserver);
        Mockito.when(game.getKeyBoardObserver()).thenReturn(keyBoardObserver);
    }

    @Test
    void testStart() {
        gameState.start();

        Mockito.verify(mouseObserver, Mockito.times(1)).setListener(pauseController);
        Mockito.verify(keyBoardObserver, Mockito.times(1)).setListener(pauseController);
    }

    @Test
    void testStep() throws IOException {
        gameState.step(game, 10);

        Mockito.verify(pauseController, Mockito.times(1)).step();
    }

    @Test
    void testGetPauseController() {
        PauseController newPauseController = new PauseController(gameState, gui);
        gameState.setPauseController(newPauseController);

        Assertions.assertEquals(gameState.getPauseController(), newPauseController);
    }

    @Test
    void testSetPauseController() {
        PauseController newPauseController = gameState.getPauseController();

        Assertions.assertEquals(newPauseController, pauseController);
    }
}