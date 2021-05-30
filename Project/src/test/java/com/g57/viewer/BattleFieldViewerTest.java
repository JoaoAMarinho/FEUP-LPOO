package com.g57.viewer;

import com.g57.Game;
import com.g57.gui.GUI;
import com.g57.gui.LanternaGUI;
import com.g57.model.Position;
import com.g57.model.battlefield.Battlefield;
import com.g57.model.battlefield.BattlefieldLoader;
import com.g57.model.element.Bomb;
import com.g57.model.element.Portal;
import com.g57.model.element.Wall;
import com.g57.model.element.button.Button;
import com.g57.model.element.enemy.Enemy;
import com.g57.model.element.obstacle.Obstacle;
import com.g57.viewer.state.EndGameViewer;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BattleFieldViewerTest {
    BattleFieldViewer battleFieldViewer;
    Battlefield battlefield;
    GUI gui;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException, FontFormatException {
        this.gui = Mockito.mock(LanternaGUI.class);
        Game game = Mockito.mock(Game.class);
        this.battlefield = new BattlefieldLoader(1).createBattlefield(20, 20);

        this.battleFieldViewer = new BattleFieldViewer(gui,battlefield);
    }

    @Test
    void draw() throws IOException {
        battleFieldViewer.draw();

        for (Wall wall : battlefield.getWalls())
            Mockito.verify(gui,Mockito.times(1)).drawWall(wall.getPosition(),wall.getColor());

        for (Bomb bomb : battlefield.getBombs())
            Mockito.verify(gui,Mockito.times(1)).drawBomb(bomb.getPosition(),bomb.getColor());

        for (Portal portal : battlefield.getPortals())
            Mockito.verify(gui,Mockito.times(1)).drawPortal(portal.getPosition(),portal.getColor());

        for ( Obstacle obstacle : battlefield.getObstacles())
            Mockito.verify(gui,Mockito.times(1)).drawObstacle(obstacle.getPosition(),obstacle.getColor());

        Mockito.verify(gui,Mockito.times(1)).drawShop(battlefield.getShop().getPosition(),battlefield.getShop().getColor());
    }
}