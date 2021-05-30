package com.g57.viewer.state;

import com.g57.gui.GUI;
import com.g57.gui.LanternaGUI;
import com.g57.model.Position;
import com.g57.model.element.Player;
import com.g57.model.element.button.Button;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayingViewerTest {
    PlayingViewer playingViewer;
    Player player;
    List<Button> buttons;
    GUI gui;

    @BeforeEach
    void setUp() {
        List<String> colors = Collections.singletonList("#FFFFFF");
        this.buttons = Arrays.asList(new Button(new Position(1,2), null,colors), new Button(new Position(3,4),null,colors));
        this.gui = Mockito.mock(LanternaGUI.class);
        this.player = new Player(new Position(3,3),colors,null,10);
        this.playingViewer = new PlayingViewer(gui,buttons,player);
    }
    @Test
    void draw() throws IOException {
        playingViewer.draw();

        Mockito.verify(gui, Mockito.times(1)).drawInfo(player.getBudget(),player.getEnergy(),player.getPotionList());
    }
}