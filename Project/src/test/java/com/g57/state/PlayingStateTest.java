package com.g57.state;

import com.g57.Game;
import com.g57.controller.BattlefieldController;
import com.g57.controller.MenuController;
import com.g57.controller.PlayerController;
import com.g57.controller.PlayingController;
import com.g57.gui.GUI;
import com.g57.gui.KeyBoardObserver;
import com.g57.gui.LanternaGUI;
import com.g57.gui.MouseObserver;
import com.g57.model.Position;
import com.g57.model.Store;
import com.g57.model.battlefield.Battlefield;
import com.g57.model.element.Player;
import com.g57.model.item.Gun;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PlayingStateTest {
    private PlayingState gameState;
    private Game game;
    private GUI gui;
    private PlayingController playingController;
    private MouseObserver mouseObserver;
    private KeyBoardObserver keyBoardObserver;
    private BattlefieldController battlefieldController;

    @BeforeEach
    void setUp() throws IOException {
        this.game = Mockito.mock(Game.class);
        this.gui = Mockito.mock(LanternaGUI.class);
        this.mouseObserver = Mockito.mock(MouseObserver.class);
        this.keyBoardObserver = Mockito.mock(KeyBoardObserver.class);
        this.playingController = Mockito.mock(PlayingController.class);
        this.battlefieldController = Mockito.mock(BattlefieldController.class);

        this.gameState = new PlayingState(game, gui, 1);
        this.gameState.setPlayingController(playingController);

        Mockito.when(game.getMouseObserver()).thenReturn(mouseObserver);
        Mockito.when(game.getKeyBoardObserver()).thenReturn(keyBoardObserver);

        Mockito.when(playingController.getBattlefieldController()).thenReturn(battlefieldController);

        PlayerController playerController=Mockito.mock(PlayerController.class);
        Player player = new Player(new Position(1,1), Arrays.asList("Color"),
                new Gun(1,1,1, "color",1,1,1), 1);

        Mockito.when(battlefieldController.getPlayerController()).thenReturn(playerController);
        Mockito.when(playerController.getPlayer()).thenReturn(player);
    }

    @Test
    void testStart() {
        gameState.start();

        Mockito.verify(mouseObserver, Mockito.times(1)).setListener(playingController);
        Mockito.verify(keyBoardObserver, Mockito.times(1)).setListener(playingController);
    }

    @Test
    void testStep() throws IOException {
        gameState.step(game, 10);

        Mockito.verify(playingController, Mockito.times(1)).step(game, 10);
    }

    @Test
    void testUpLevel() throws IOException {
        gameState.setLevel(7);
        Boolean result = gameState.upLevel();
        Assertions.assertFalse(result);

        gameState.setLevel(3);
        result = gameState.upLevel();

        Mockito.verify(battlefieldController, Mockito.times(1)).resetPlayer();
        Assertions.assertTrue(result);
    }

    @Test
    void testGetPlayingController() {
        PlayingController newPlayingController = gameState.getPlayingController();

        Assertions.assertEquals(newPlayingController, playingController);
    }

    @Test
    void testSetPlayingController() {
        PlayingController newPlayingController = new PlayingController(gameState, gui, Mockito.mock(Battlefield.class),Mockito.mock(Store.class));
        gameState.setPlayingController(newPlayingController);

        Assertions.assertEquals(gameState.getPlayingController(), newPlayingController);
    }

    @Test
    void testGetLevel() {
        Assertions.assertEquals(gameState.getLevel(), 1);
    }

    @Test
    void testSetLevel() {
        gameState.setLevel(3);
        Assertions.assertEquals(gameState.getLevel(), 3);
        gameState.setLevel(4);
        Assertions.assertEquals(gameState.getLevel(), 4);
    }
}