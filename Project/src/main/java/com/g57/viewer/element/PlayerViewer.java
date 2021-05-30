package com.g57.viewer.element;

import com.g57.gui.GUI;
import com.g57.model.element.Player;

public class PlayerViewer implements ElementViewer<Player> {

    @Override
    public void drawElement(Player element, GUI gui) {
        gui.drawPlayer(element.getPosition(), element.getColor());
    }
}

