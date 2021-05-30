package com.g57.viewer.element;

import com.g57.gui.GUI;
import com.g57.model.Position;
import com.g57.model.element.Player;
import com.g57.model.element.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class WallViewerTest {

    Wall wall;
    WallViewer viewer;
    GUI gui;

    @BeforeEach
    void setUp() {
        this.wall = new Wall(new Position(4,4),"#FFFFFF");
        viewer = new WallViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawElement() {
        viewer.drawElement(wall, gui);
        Mockito.verify(gui, Mockito.times(1)).drawWall(wall.getPosition(),wall.getColor());
    }
}