package com.g57.viewer.element;

import com.g57.gui.GUI;
import com.g57.model.element.bullet.Bullet;

import java.util.Arrays;
import java.util.List;

public class BulletViewer implements ElementViewer<Bullet>{
    private static final List<List<String>> paint =  Arrays.asList(Arrays.asList("!", "&", "$"),
                                                            Arrays.asList("[", "]", "{"),
                                                            Arrays.asList("}", "=", "%"));
    @Override
    public void drawElement(Bullet element, GUI gui) {
        gui.drawBullet(element.getPosition(), element.getColor(), paint.get(element.getIndexY()).get(element.getIndexX()));
    }
}
