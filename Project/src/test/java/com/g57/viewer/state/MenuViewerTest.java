package com.g57.viewer.state;

import com.g57.gui.GUI;
import com.g57.gui.LanternaGUI;
import com.g57.model.Position;
import com.g57.model.element.button.Button;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MenuViewerTest {
    MenuViewer menuViewer;
    TextGraphics tg;
    GUI gui;
    List<Button> buttons;

    @BeforeEach
    void setUp() {
        this.tg = Mockito.mock(TextGraphics.class);
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(tg);
        this.gui = new LanternaGUI(screen);

        List<String> colors = Collections.singletonList("#000000");
        this.buttons = Arrays.asList(new Button(new Position(1,2), null,colors), new Button(new Position(3,4),null,colors));
        this.menuViewer = new MenuViewer(gui, buttons);
    }

    @Test
    void draw() throws IOException {
        menuViewer.draw();

        Mockito.verify(tg, Mockito.times(buttons.size() + 1)).setBackgroundColor(TextColor.Factory.fromString("#000000"));
    }
}