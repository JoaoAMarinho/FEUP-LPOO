package com.g57.viewer.element;

import com.g57.gui.GUI;
import com.g57.model.element.Element;

public interface ElementViewer<T extends Element> {
    void drawElement(T element, GUI gui);
}


