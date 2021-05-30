package com.g57.viewer.state;

import com.g57.gui.GUI;
import com.g57.gui.LanternaGUI;
import com.g57.model.Position;
import com.g57.model.element.button.Button;
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

class LoadingLevelViewerTest {
    LoadingLevelViewer loadingLevelViewer;
    TextGraphics tg;
    GUI gui;

    @BeforeEach
    void setUp() {
        this.gui = Mockito.mock(LanternaGUI.class);
        List<String> colors = Collections.singletonList("#FFFFFF");
        List<Button> buttons = Arrays.asList(new Button(new Position(1,2), null,colors), new Button(new Position(3,4),null,colors));
        this.loadingLevelViewer = new LoadingLevelViewer(gui,buttons,1);
    }

    @Test
    void draw() throws IOException {
       loadingLevelViewer.draw();

       Mockito.verify(gui, Mockito.times(1)).drawTitle(new Position(7,8),"LOADING...", "#000000","#FFFFFF");
    }
}