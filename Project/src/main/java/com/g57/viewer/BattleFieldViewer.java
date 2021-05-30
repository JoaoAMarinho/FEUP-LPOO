package com.g57.viewer;

import com.g57.gui.GUI;
import com.g57.model.element.Element;
import com.g57.viewer.element.*;
import com.g57.model.battlefield.Battlefield;

import java.io.IOException;
import java.util.List;

public class BattleFieldViewer {
    private final GUI gui;
    private final Battlefield battlefield;


    public BattleFieldViewer(GUI gui, Battlefield battlefield) {
        this.gui = gui;
        this.battlefield = battlefield;
    }

    public void draw() throws IOException {
        gui.clear();

        drawBackground();
        drawElements(this.battlefield.getEnemies(), new EnemyViewer());
        drawElements(this.battlefield.getBullets(), new BulletViewer());
        drawElement(this.battlefield.getPlayer(), new PlayerViewer());
        drawElements(this.battlefield.getPortals(), new PortalViewer());
        drawElements(this.battlefield.getBombs(), new BombViewer());
        drawElements(this.battlefield.getObstacles(), new ObstacleViewer());
        drawElements(this.battlefield.getWalls(), new WallViewer());
        drawElement(this.battlefield.getShop(), new ShopViewer());

        gui.refresh();
    }

    private void drawBackground() {
    }


    private <T extends Element> void drawElements(List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements)
            drawElement(element, viewer);
    }

    private <T extends Element> void drawElement(T element, ElementViewer<T> viewer) {
        viewer.drawElement(element, gui);
    }


}
