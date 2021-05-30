package com.g57.viewer.element;

import com.g57.gui.GUI;
import com.g57.model.element.enemy.BigEnemy;
import com.g57.model.element.enemy.Enemy;
import com.g57.model.element.enemy.MediumEnemy;
import com.g57.model.element.enemy.SmallEnemy;

public class EnemyViewer implements ElementViewer<Enemy> {
    @Override
    public void drawElement(Enemy element, GUI gui) {
        if (element instanceof SmallEnemy) drawSmall(element, gui);
        else if ( element instanceof MediumEnemy) drawMedium(element, gui);
        else if ( element instanceof  BigEnemy) drawBig(element, gui);
    }

    public void drawSmall(Enemy element, GUI gui) {
        gui.drawSmallEnemy(element.getPosition(), element.getColor());
    }
    public void drawMedium(Enemy element, GUI gui) {
        gui.drawMediumEnemy(element.getPosition(), element.getColor());
    }
    public void drawBig(Enemy element, GUI gui) {
        gui.drawBigEnemy(element.getPosition(), element.getColor());
    }

}
