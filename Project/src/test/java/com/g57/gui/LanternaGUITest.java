package com.g57.gui;

import com.g57.controller.MenuController;
import com.g57.model.Position;
import com.g57.model.item.Item;
import com.g57.state.GameState;
import com.g57.state.listener.KeyBoardListener;
import com.g57.state.listener.MouseListener;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LanternaGUITest {
    private LanternaGUI gui;
    private TextGraphics tg;

    @BeforeEach
    void setUp() {
        this.tg = Mockito.mock(TextGraphics.class);
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        this.gui = new LanternaGUI(screen);
        Mockito.when(screen.newTextGraphics()).thenReturn(tg);
    }

    @Test
    void drawPortal() {
        Position position = new Position(3,5);
        String color = "#000000";
        gui.drawPortal(position,color);
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString(color));
        Mockito.verify(tg, Mockito.times(1)).fillRectangle(new TerminalPosition(position.getX(), position.getY()),new TerminalSize(1, 1), ' ' );
    }

    @Test
    void drawBomb() {
        Position position = new Position(3,5);
        String color = "#000000";
        gui.drawBomb(position,color);

        Mockito.verify(tg, Mockito.times(1)).putString(position.getX(), position.getY(), "f");
    }

    @Test
    void drawExplosion() {
        Position position = new Position(3,5);
        String color = "#000000";
        gui.drawExplosion(position,color);

        Mockito.verify(tg, Mockito.times(1)).putString(position.getX(), position.getY(), "g");
    }

    @Test
    void drawObstacle() {
        Position position = new Position(3,5);
        String color = "#000000";
        gui.drawObstacle(position,color);

        Mockito.verify(tg, Mockito.times(1)).putString(position.getX(), position.getY(), "h");
    }

    @Test
    void drawWall() {
        Position position = new Position(3,5);
        String color = "#000000";
        gui.drawWall(position,color);

    }

    @Test
    void drawBullet() {
        Position position = new Position(3,5);
        String color = "#000000";
        gui.drawBullet(position,color,".");

        Mockito.verify(tg, Mockito.times(1)).putString(position.getX(), position.getY(), ".");
    }

    @Test
    void drawPlayer() {
        Position position = new Position(3,5);
        String color = "#000000";
        gui.drawPlayer(position,color);

        Mockito.verify(tg, Mockito.times(1)).putString(position.getX(), position.getY(), "n");
    }

    @Test
    void drawShop() {
        Position position = new Position(3,5);
        String color = "#000000";
        gui.drawShop(position,color);

        Mockito.verify(tg, Mockito.times(1)).putString(position.getX(), position.getY(), "\\");
    }

    @Test
    void drawButton() {
        Position position = new Position(3,5);
        String color = "#000000";
        gui.drawButton(position,position,"buttontest",color,color,10,10);

        Mockito.verify(tg, Mockito.times(1)).putString(position.getX(), position.getY(), "buttontest");
    }

    @Test
    void drawBigEnemy() {
        Position position = new Position(3,5);
        String color = "#000000";
        gui.drawBigEnemy(position,color);

        Mockito.verify(tg, Mockito.times(1)).putString(position.getX(), position.getY(), "q");
    }

    @Test
    void drawMediumEnemy() {        Position position = new Position(3,5);
        String color = "#000000";
        gui.drawMediumEnemy(position,color);

        Mockito.verify(tg, Mockito.times(1)).putString(position.getX(), position.getY(), "p");
    }

    @Test
    void drawSmallEnemy() {
        Position position = new Position(3,5);
        String color = "#000000";
        gui.drawSmallEnemy(position,color);

        Mockito.verify(tg, Mockito.times(1)).putString(position.getX(), position.getY(), "o");
    }

    @Test
    void drawTitle() {
        Position position = new Position(3,5);
        String color = "#000000";
        gui.drawTitle(position,"stringtest",color,color);

        Mockito.verify(tg, Mockito.times(1)).putString(position.getX(), position.getY(), "stringtest");
    }


    @Test
    void drawInfo() {
        Map<Item, Integer> potions = new HashMap<>();
        Position position = new Position(3,5);
        String color = "#000000";
        gui.drawInfo(10, 10, potions);

        Mockito.verify(tg, Mockito.times(1)).putString(0, gui.getHeight(), String.valueOf(10));
    }

    @Test
    void drawRectangle() {
        Position position = new Position(3,5);
        gui.drawRectangle(tg,"#FFFFFF",20,20,position);

        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
    }

    @Test
    void drawBackground() {
        Position position = new Position(3,5);
        gui.drawRectangle(tg,"#FFFFFF",20,20,position);

        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
    }
}