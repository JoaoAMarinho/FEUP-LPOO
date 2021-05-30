package com.g57.viewer.element;

import com.g57.gui.GUI;
import com.g57.model.element.Shop;

public class ShopViewer implements ElementViewer<Shop> {
    @Override
    public void drawElement(Shop element, GUI gui) {
        gui.drawShop(element.getPosition(), element.getColor());
    }
}
