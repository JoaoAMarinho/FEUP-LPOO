package com.g57.model.item.command;

import com.g57.controller.PlayerController;
import com.g57.gui.GUI;
import com.g57.gui.LanternaGUI;
import com.g57.model.Position;
import com.g57.model.element.Player;
import com.g57.model.item.Gun;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DisplayInfoCommandTest {
    DisplayInfoCommand displayInfoCommand;
    Position position;
    TextGraphics tg = Mockito.mock(TextGraphics.class);

    @BeforeEach
    void setUp() {
        this.tg = Mockito.mock(TextGraphics.class);
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(tg);
        GUI gui = new LanternaGUI(screen);
        position = Mockito.mock(Position.class);
        displayInfoCommand = new DisplayInfoCommand(gui,position);
        Gun gun= new Gun(1,2,2,"#FFFFFF",3,3,1);
        displayInfoCommand.setPlayerController(new PlayerController(new Player(position, Collections.emptyList(),gun,19)));
    }

    @Test
    void execute() {
        displayInfoCommand.execute();
        Mockito.verify(tg, Mockito.times(3)).setBackgroundColor(TextColor.Factory.fromString("#ffffdf"));
    }

    @Test
    void undo() {
        displayInfoCommand.undo();
        Mockito.verify(tg, Mockito.times(4)).setBackgroundColor(TextColor.Factory.fromString("#000000"));
    }
}