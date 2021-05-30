package com.g57.state;

import com.g57.Game;
import com.g57.controller.MenuController;
import com.g57.gui.GUI;
import com.g57.gui.KeyBoardObserver;
import com.g57.gui.LanternaGUI;
import com.g57.gui.MouseObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

class MenuStateTest {
    private MenuState gameState;
    private Game game;
    private GUI gui;
    private MenuController menuController;
    private MouseObserver mouseObserver;
    private KeyBoardObserver keyBoardObserver;

    @BeforeEach
    void setUp() throws IOException {
        this.game = Mockito.mock(Game.class);
        this.gui = Mockito.mock(LanternaGUI.class);
        this.mouseObserver = Mockito.mock(MouseObserver.class);
        this.keyBoardObserver = Mockito.mock(KeyBoardObserver.class);

        this.gameState = new MenuState(game, gui);
        this.menuController = Mockito.mock(MenuController.class);
        this.gameState.setMenuController(menuController);

        Mockito.when(game.getMouseObserver()).thenReturn(mouseObserver);
        Mockito.when(game.getKeyBoardObserver()).thenReturn(keyBoardObserver);
    }

    @Test
    void testStart() {
        gameState.start();

        Mockito.verify(mouseObserver, Mockito.times(1)).setListener(menuController);
        Mockito.verify(keyBoardObserver, Mockito.times(1)).setListener(menuController);
    }

    @Test
    void testStep() throws IOException {
        gameState.step(game, 10);

        Mockito.verify(menuController, Mockito.times(1)).step();
    }

    @Test
    void testSetMenuController() {
        MenuController newMenuController = new MenuController(gameState, gui);
        gameState.setMenuController(newMenuController);

        Assertions.assertEquals(gameState.getMenuController(), newMenuController);
    }

    @Test
    void testGetMenuController() {
        MenuController newMenuController = gameState.getMenuController();

        Assertions.assertEquals(newMenuController, menuController);
    }
}