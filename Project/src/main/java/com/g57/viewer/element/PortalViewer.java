package com.g57.viewer.element;

import com.g57.gui.GUI;
import com.g57.model.element.Portal;

public class PortalViewer implements ElementViewer<Portal> {
    @Override
    public void drawElement(Portal element, GUI gui) {
        gui.drawPortal(element.getPosition(), element.getColor());
    }
}
