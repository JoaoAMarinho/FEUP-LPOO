package com.g57.controller;

import com.g57.Game;
import com.g57.gui.GUI;
import com.g57.gui.LanternaGUI;
import com.g57.model.element.button.Button;
import com.g57.model.item.command.Command;
import com.g57.model.item.command.MenuButtonCommand;
import com.g57.state.InstructionsState;
import com.g57.state.MenuState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MenuControllerTest {
    MenuController menuController;
    MenuState menuState;
    @BeforeEach
    void setUp() throws IOException {
        Game game = Mockito.mock(Game.class);
        Mockito.when(game.getHeight()).thenReturn(1);
        Mockito.when(game.getWidth()).thenReturn(1);
        GUI gui = Mockito.mock(LanternaGUI.class);
        this.menuState = new MenuState(game, gui);
        this.menuController = new MenuController(menuState,gui);
    }
    @Test
    void click() {
        List<Button> buttons = menuState.getButtons();
        Button button = buttons.get(buttons.size()-1);
        MenuButtonCommand command = Mockito.mock(MenuButtonCommand.class);
        button.setCommand(command);

        menuController.click(button.getPosition());
        Mockito.verify(command, Mockito.times(1)).execute();
    }

    @Test
    void move() {
        List<Button> buttons = menuState.getButtons();
        Button button = buttons.get(buttons.size() - 1);
        menuController.move(button.getPosition());
        Assertions.assertTrue(button.isHighlighted());
    }
}