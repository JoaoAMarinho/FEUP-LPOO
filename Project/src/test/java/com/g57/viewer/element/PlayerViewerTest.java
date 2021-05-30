package com.g57.viewer.element;

import com.g57.gui.GUI;
import com.g57.model.Position;
import com.g57.model.element.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PlayerViewerTest {

    Player player;
    PlayerViewer viewer;
    GUI gui;

    @BeforeEach
    void setUp() {
        this.player = new Player(new Position(4,4), Arrays.asList("#FFFFFF"),null,10);
        viewer = new PlayerViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawElement() {
        viewer.drawElement(player, gui);
        Mockito.verify(gui, Mockito.times(1)).drawPlayer(player.getPosition(),player.getColor());
    }
}