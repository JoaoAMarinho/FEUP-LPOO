package com.g57.viewer.element;

import com.g57.gui.GUI;
import com.g57.model.element.Bomb;

public class BombViewer implements ElementViewer<Bomb> {

    @Override
    public void drawElement(Bomb element, GUI gui) {
        if(element.isActive())
            gui.drawExplosion(element.getPosition(), element.getColor());
        else
            gui.drawBomb(element.getPosition(), element.getColor());
    }
}
