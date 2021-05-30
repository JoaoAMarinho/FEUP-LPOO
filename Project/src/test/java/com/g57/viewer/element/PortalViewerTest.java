package com.g57.viewer.element;

import com.g57.gui.GUI;
import com.g57.gui.LanternaGUI;
import com.g57.model.Position;
import com.g57.model.element.Portal;
import com.g57.model.element.bullet.Bullet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PortalViewerTest {
    Portal portal;
    PortalViewer portalViewer;
    GUI gui;

    @BeforeEach
    void setUp() {
        Position position = new Position(3,5);
        this.portal = new Portal(position);
        this.portalViewer= new PortalViewer();
        this.gui = Mockito.mock(LanternaGUI.class);

    }

    @Test
    void drawElement() {
        portalViewer.drawElement(portal,gui);
        Mockito.verify(gui, Mockito.times(1)).drawPortal(portal.getPosition(), portal.getColor());
    }
}