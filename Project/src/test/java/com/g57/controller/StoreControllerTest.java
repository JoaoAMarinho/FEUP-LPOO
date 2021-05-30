package com.g57.controller;

import com.g57.Game;
import com.g57.gui.GUI;
import com.g57.gui.LanternaGUI;
import com.g57.model.Position;
import com.g57.model.Store;
import com.g57.model.battlefield.Battlefield;
import com.g57.model.element.Player;
import com.g57.model.element.button.Button;
import com.g57.model.item.Gun;
import com.g57.model.item.command.Command;
import com.g57.model.item.command.MenuButtonCommand;
import com.g57.state.PlayingState;
import com.g57.state.StoreState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StoreControllerTest {
    StoreController storeController;
    StoreState storeState;

    @BeforeEach
    void setUp() throws IOException {
        Game game = Mockito.mock(Game.class);
        Player player = new Player(new Position(3,4), Arrays.asList(),new Gun(10,10,10,"",10,10,10),10);
        Mockito.when(game.getHeight()).thenReturn(1);
        Mockito.when(game.getWidth()).thenReturn(1);
        GUI gui = Mockito.mock(LanternaGUI.class);
        Store store = new Store(gui);
        this.storeState = new StoreState(game, gui,new PlayingState(game,gui,1),player, store);
        this.storeController = new StoreController(storeState, gui, player,store);
    }

    @Test
    void step() {

    }


    @Test
    void click() {
        //TODO
        /*List<Button> buttons = storeState.getButtons();
        Button button = buttons.get(buttons.size() - 1);
        Command command = Mockito.mock(Command.class);
        button.setCommand(command);

        storeController.click(button.getPosition());
        Mockito.verify(command, Mockito.times(1)).execute();*/
    }

    @Test
    void move() {
        List<Button> buttons = storeState.getButtons();
        Button button = buttons.get(buttons.size() - 1);
        storeController.move(button.getPosition());
        Assertions.assertTrue(button.isHighlighted());
    }
}