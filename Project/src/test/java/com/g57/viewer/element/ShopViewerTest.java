package com.g57.viewer.element;

import com.g57.gui.GUI;
import com.g57.gui.LanternaGUI;
import com.g57.model.Position;
import com.g57.model.element.Shop;
import com.g57.model.element.bullet.Bullet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ShopViewerTest {
    Shop shop;
    ShopViewer shopViewer;
    GUI gui;

    @BeforeEach
    void setUp() {
        Position position = new Position(3,5);
        this.shop = new Shop(position,"#FFFFFF");
        this.shopViewer= new ShopViewer();
        this.gui = Mockito.mock(LanternaGUI.class);

    }

    @Test
    void drawElement() {
        shopViewer.drawElement(shop,gui);
        Mockito.verify(gui, Mockito.times(1)).drawShop(shop.getPosition(), shop.getColor());
    }
}