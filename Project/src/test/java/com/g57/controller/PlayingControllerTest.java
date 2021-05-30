package com.g57.controller;

import com.g57.Game;
import com.g57.gui.GUI;
import com.g57.gui.LanternaGUI;
import com.g57.model.Store;
import com.g57.model.battlefield.Battlefield;
import com.g57.model.element.Shop;
import com.g57.model.element.button.Button;
import com.g57.model.item.command.MenuButtonCommand;
import com.g57.state.GameState;
import com.g57.state.PauseState;
import com.g57.state.PlayingState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayingControllerTest {
    PlayingController playingController;
    GameState playingState;

    @BeforeEach
    void setUp() throws IOException {
        Game game = Mockito.mock(Game.class);
        Mockito.when(game.getHeight()).thenReturn(1);
        Mockito.when(game.getWidth()).thenReturn(1);
        GUI gui = Mockito.mock(LanternaGUI.class);
        this.playingState = new PlayingState(game, gui,1);
        this.playingController = new PlayingController(playingState, gui, new Battlefield(),new Store(gui));
    }

    @Test
    void click() {
        List<Button> buttons = playingState.getButtons();
        Button button = buttons.get(buttons.size()-1);
        MenuButtonCommand command = Mockito.mock(MenuButtonCommand.class);
        button.setCommand(command);

        playingController.click(button.getPosition());
        Mockito.verify(command, Mockito.times(1)).execute();
    }


    @Test
    void checkButtons() {
        List<Button> buttons = playingState.getButtons();
        Button button = buttons.get(buttons.size()-1);
        button.isActive();

        playingController.checkButtons();

        Assertions.assertFalse(button.isActive());
    }


    @Test
    void setupModel() {
        Battlefield battlefield = new Battlefield();
        playingController.setupModel(battlefield);
        Battlefield testBattlefield = playingController.getModel();

        Assertions.assertEquals(battlefield,testBattlefield);
    }
}