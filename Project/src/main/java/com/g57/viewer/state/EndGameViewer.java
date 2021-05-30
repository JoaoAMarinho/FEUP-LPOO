package com.g57.viewer.state;

import com.g57.gui.GUI;
import com.g57.model.Position;
import com.g57.model.element.button.Button;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EndGameViewer extends StateViewer {
    private final boolean won;

    public EndGameViewer(GUI gui, List<Button> buttons, boolean won) {
        super(gui, buttons);
        this.won = won;
    }

    @Override
    public void draw() throws IOException {
        try {
            gui.drawRectangle(gui.createTextGraphics(), "#000000", 15, 5, new Position(6, 10));
            if (won)
                gui.drawTitle(new Position(8, 12), "YOU WON n", "#000000", "#FFFFFF");
            else
                gui.drawTitle(new Position(7, 12), "YOU LOST o", "#000000", "#FFFFFF");
            gui.refresh();

            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (Exception e) {
            System.out.println("ERROR"); //TODO
        }
    }
}
