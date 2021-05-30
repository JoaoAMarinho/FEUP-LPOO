package com.g57.viewer.element;

import com.g57.gui.GUI;
import com.g57.gui.LanternaGUI;
import com.g57.model.Position;
import com.g57.model.element.Bomb;
import com.g57.model.element.bullet.Bullet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class BulletViewerTest {
    Bullet bullet;
    BulletViewer bulletViewer;
    GUI gui;

    @BeforeEach
    void setUp() {
        Position position = new Position(3,5);
        this.bullet = new Bullet(4,5,"#FFFFFF",true);
        this.bulletViewer= new BulletViewer();
        this.gui = Mockito.mock(LanternaGUI.class);

    }

    @Test
    void drawElement() {
        bulletViewer.drawElement(bullet,gui);
        Mockito.verify(gui, Mockito.times(1)).drawBullet(bullet.getPosition(), bullet.getColor(),"!");
    }
}