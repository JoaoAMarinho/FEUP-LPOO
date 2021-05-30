package com.g57.viewer;

import com.g57.gui.GUI;
import com.g57.gui.LanternaGUI;
import com.g57.model.Position;
import com.g57.model.element.Player;
import com.g57.model.item.Gun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GunInfoViewerTest {
    GunInfoViewer gunInfoViewer;
    GUI gui;
    Player player;

    @BeforeEach
    void setUp() {
        this.gui = Mockito.mock(LanternaGUI.class);
        this.gunInfoViewer = new GunInfoViewer(gui,new Position(4,5));
        Gun gun = new Gun(10,3,1,"#ffffff", 10,4, 1);
        List<String> colors = Collections.singletonList("#FFFFFF");
        this.player = new Player(new Position(0,3),colors,gun,10);
        gunInfoViewer.setPlayer(player);
    }
    @Test
    void draw() {
        gunInfoViewer.draw();
        List<String> characteristics = player.getGun().getCharacteristics();

        int delta = 0;
        for (int i = 0; i < 3; i++) {
            Mockito.verify(gui, Mockito.times(1)).drawTitle(new Position(4,5+delta), characteristics.get(i),"#ffffdf","#000000");
            delta++;
        }
    }

    @Test
    void undo() {
        gunInfoViewer.undo();
        List<String> characteristics = player.getGun().getCharacteristics();

        int delta = 0;
        for (String str : characteristics) {
            Mockito.verify(gui, Mockito.times(1)).drawTitle(new Position(4,5+delta),str,"#000000", "#000000");
            delta++;
        }
    }
}