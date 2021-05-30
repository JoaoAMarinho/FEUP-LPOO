package com.g57.viewer.element;

import com.g57.gui.GUI;
import com.g57.gui.LanternaGUI;
import com.g57.model.Position;
import com.g57.model.element.Bomb;
import com.g57.state.PauseState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class BombViewerTest {
    Bomb bomb;
    BombViewer bombViewer;
    GUI gui;

    @BeforeEach
    void setUp() {
        Position position = new Position(3,5);
        this.bomb = new Bomb(position,"#FFFFFF",4);
        this.bombViewer = new BombViewer();
        this.gui = Mockito.mock(LanternaGUI.class);

    }

    @Test
    void drawElement() {
        bombViewer.drawElement(bomb,gui);
        Mockito.verify(gui, Mockito.times(1)).drawBomb(bomb.getPosition(), bomb.getColor());
    }
}