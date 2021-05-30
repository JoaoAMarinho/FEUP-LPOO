package com.g57.viewer.element;

import com.g57.gui.GUI;
import com.g57.model.element.obstacle.Obstacle;

public class ObstacleViewer implements ElementViewer<Obstacle> {
    @Override
    public void drawElement(Obstacle element, GUI gui) {
            gui.drawObstacle(element.getPosition(), element.getColor());
    }
}
