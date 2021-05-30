package com.g57.viewer.element;

import com.g57.gui.GUI;
import com.g57.gui.LanternaGUI;
import com.g57.model.Position;
import com.g57.model.element.bullet.Bullet;
import com.g57.model.element.obstacle.Obstacle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ObstacleViewerTest {
    Obstacle obstacle;
    ObstacleViewer obstacleViewer;
    GUI gui;

    @BeforeEach
    void setUp() {
        Position position = new Position(3,5);
        this.obstacle = new Obstacle(position,"#FFFFFF",10);
        this.obstacleViewer= new ObstacleViewer();
        this.gui = Mockito.mock(LanternaGUI.class);

    }

    @Test
    void drawElement() {
        obstacleViewer.drawElement(obstacle,gui);
        Mockito.verify(gui, Mockito.times(1)).drawObstacle(obstacle.getPosition(), obstacle.getColor());
    }
}