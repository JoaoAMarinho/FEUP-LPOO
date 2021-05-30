package com.g57.viewer;

import com.g57.gui.GUI;
import com.g57.model.Position;
import com.g57.model.element.Player;

import java.io.IOException;
import java.util.List;

public class GunInfoViewer {
    private Player player;
    private final GUI gui;
    private final Position position;

    public GunInfoViewer(GUI gui, Position position) {
        this.gui = gui;
        this.position = position;
    }

    public void draw() {
        int delta = 0;
        List<String> characteristics = player.getGun().getCharacteristics();

        for (int i = 0; i < 3; i ++){
            gui.drawTitle(new Position(position.getX() , position.getY()+delta), characteristics.get(i), "#ffffdf", "#000000");
            delta++;
        }

        try {
            gui.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void undo() {
        int delta = 0;
        for (String str : player.getGun().getCharacteristics()){
            gui.drawTitle(new Position(position.getX() , position.getY()+delta), str, "#000000", "#000000");
            delta++;
        }
        try {
            gui.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
