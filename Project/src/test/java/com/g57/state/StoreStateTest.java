package com.g57.state;

import com.g57.Game;
import com.g57.controller.MenuController;
import com.g57.controller.StoreController;
import com.g57.gui.GUI;
import com.g57.gui.KeyBoardObserver;
import com.g57.gui.LanternaGUI;
import com.g57.gui.MouseObserver;
import com.g57.model.Position;
import com.g57.model.Store;
import com.g57.model.element.Player;
import com.g57.model.item.Gun;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StoreStateTest {
    private StoreState gameState;
    private Game game;
    private GUI gui;
    private StoreController storeController;
    private MouseObserver mouseObserver;
    private KeyBoardObserver keyBoardObserver;
    private Player player;
    private Store store;

    @BeforeEach
    void setUp() throws IOException {
        this.game = Mockito.mock(Game.class);
        this.gui = Mockito.mock(LanternaGUI.class);
        this.mouseObserver = Mockito.mock(MouseObserver.class);
        this.keyBoardObserver = Mockito.mock(KeyBoardObserver.class);
        this.store = new Store(gui);
        this.player = new Player(new Position(1,1), Arrays.asList("Color"),
                new Gun(1,1,1, "color",1,1,1), 1);

        this.gameState = new StoreState(game, gui, Mockito.mock(GameState.class), player, store);


        this.storeController = Mockito.mock(StoreController.class);
        this.gameState.setStoreController(storeController);

        Mockito.when(game.getMouseObserver()).thenReturn(mouseObserver);
        Mockito.when(game.getKeyBoardObserver()).thenReturn(keyBoardObserver);
    }

    @Test
    void testStart() {
        gameState.start();

        Mockito.verify(mouseObserver, Mockito.times(1)).setListener(storeController);
        Mockito.verify(keyBoardObserver, Mockito.times(1)).setListener(storeController);
    }

    @Test
    void testStep() throws IOException {
        gameState.step(game, 10);

        Mockito.verify(storeController, Mockito.times(1)).step();
    }

    @Test
    void testGetStoreController() {
        StoreController newStoreController = new StoreController(gameState, gui, player, store);
        gameState.setStoreController(newStoreController);

        Assertions.assertEquals(gameState.getStoreController(), newStoreController);
    }

    @Test
    void testSetStoreController() {
        StoreController newStoreController = gameState.getStoreController();

        Assertions.assertEquals(newStoreController, storeController);
    }
}