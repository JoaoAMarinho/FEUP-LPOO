package com.g57.viewer.element;

import com.g57.gui.GUI;
import com.g57.model.element.Wall;

public class WallViewer implements ElementViewer<Wall> {

    @Override
    public void drawElement(Wall element, GUI gui) {
        gui.drawWall(element.getPosition(), element.getColor());
    }
}
