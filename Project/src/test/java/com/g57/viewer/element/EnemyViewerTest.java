package com.g57.viewer.element;

import com.g57.gui.GUI;
import com.g57.gui.LanternaGUI;
import com.g57.model.Position;
import com.g57.model.element.bullet.Bullet;
import com.g57.model.element.enemy.BigEnemy;
import com.g57.model.element.enemy.Enemy;
import com.g57.model.element.enemy.MediumEnemy;
import com.g57.model.element.enemy.SmallEnemy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class EnemyViewerTest {
    BigEnemy bigEnemy;
    SmallEnemy smallEnemy;
    MediumEnemy mediumEnemy;
    EnemyViewer enemyViewer;
    GUI gui;

    @BeforeEach
    void setUp() {
        Position position = new Position(3,5);
        this.bigEnemy = new BigEnemy();
        this.smallEnemy = new SmallEnemy();
        this.mediumEnemy = new MediumEnemy();
        this.enemyViewer= new EnemyViewer();
        this.gui = Mockito.mock(LanternaGUI.class);
    }

    @Test
    void drawElement() {
        Enemy enemy = bigEnemy;
        enemyViewer.drawElement(enemy,gui);
        Mockito.verify(gui, Mockito.times(1)).drawBigEnemy(enemy.getPosition(), enemy.getColor());

        enemy = smallEnemy;
        enemyViewer.drawElement(enemy,gui);
        Mockito.verify(gui, Mockito.times(1)).drawSmallEnemy(enemy.getPosition(), enemy.getColor());

        enemy = mediumEnemy;
        enemyViewer.drawElement(enemy,gui);
        Mockito.verify(gui, Mockito.times(1)).drawMediumEnemy(enemy.getPosition(), enemy.getColor());
    }

    @Test
    void drawSmall() {
        Enemy enemy = smallEnemy;
        enemyViewer.drawSmall(enemy,gui);
        Mockito.verify(gui, Mockito.times(1)).drawSmallEnemy(enemy.getPosition(), enemy.getColor());
    }

    @Test
    void drawMedium() {
        Enemy enemy = mediumEnemy;
        enemyViewer.drawMedium(enemy,gui);
        Mockito.verify(gui, Mockito.times(1)).drawMediumEnemy(enemy.getPosition(), enemy.getColor());
    }

    @Test
    void drawBig() {
        Enemy enemy = bigEnemy;
        enemyViewer.drawBig(enemy,gui);
        Mockito.verify(gui, Mockito.times(1)).drawBigEnemy(enemy.getPosition(), enemy.getColor());
    }




}