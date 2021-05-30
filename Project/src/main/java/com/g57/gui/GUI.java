package com.g57.gui;

import com.g57.model.Position;
import com.g57.model.item.Item;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.Map;

public interface GUI {

    enum ACTION {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        SHOP,
        QUIT
    }

    TextGraphics createTextGraphics();

    int getWidth();

    int getHeight();

    void drawRectangle(TextGraphics textGraphics, String color, int width, int height, Position pos);

    void drawBackground(TextGraphics textGraphics, String color);

    void clear() throws IOException;

    void refresh() throws IOException;

    void close() throws IOException;

    void addMouseListener(MouseObserver obs);

    void addKeyBoardListener(KeyBoardObserver obs);

    void drawInfo(int coins, int energy, Map<Item, Integer> potionN);

    boolean isActive();

    void drawPortal(Position position, String color);

    void drawBomb(Position position, String color);

    void drawExplosion(Position position, String color);

    void drawObstacle(Position position, String color);

    void drawWall(Position position, String color);

    void drawBullet(Position position, String elementColor, String character);

    void drawPlayer(Position position, String color);

    void drawShop(Position position, String color);

    void drawButton(Position bPos, Position tPos, String text, String bgColor, String textColor, int width, int height);

    void drawBigEnemy(Position position, String color);

    void drawMediumEnemy(Position position, String color);

    void drawSmallEnemy(Position position, String color);

    void drawTitle(Position position, String text, String color, String colorText);
}
