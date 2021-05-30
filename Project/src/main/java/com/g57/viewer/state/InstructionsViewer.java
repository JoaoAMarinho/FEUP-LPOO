package com.g57.viewer.state;

import com.g57.gui.GUI;
import com.g57.model.Position;
import com.g57.model.element.button.Button;
import com.g57.viewer.element.ButtonViewer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class InstructionsViewer extends StateViewer {

    public InstructionsViewer(GUI gui, List<Button> buttons) {
        super(gui, buttons);
    }

    @Override
    public void draw() throws IOException {
        //Draw menu background
        gui.clear();

        //Draw Shop Title
        drawText(new Position(6,3),"INSTRUCTIONS", "#000000", "#FFFFFF");
        drawText(new Position(1,7),"HELLO ADVENTURER", "#000000", "#FFFFFF");


        drawText(new Position(1,10),"1.MOVE WITH", "#000000", "#FFFFFF");
        List<String> keys = Arrays.asList("W", "A", "S", "D");
        for (int i = 0; i < keys.size(); i++) {
            drawText(new Position(13+i+i,10),keys.get(i), "#000000", "#FFFF00");
        }

        drawText(new Position(1,12),"2.AIM/SHOOT WITH MOUSE", "#000000", "#FFFFFF");
        drawText(new Position(1,14),"3.USE ", "#000000", "#FFFFFF");
        drawText(new Position(7,14), "P", "#000000", "#FFFF00");
        drawText(new Position(9,14),"TO ACCESS THE", "#000000", "#FFFFFF");
        drawText(new Position(0,15),"   SHOP", "#000000", "#FFFFFF");
        drawText(new Position(1,17),"4.", "#000000", "#FFFFFF");
        drawText(new Position(3,17), "ESC", "#000000", "#FFFF00");
        drawText(new Position(7,17),"TO PAUSE THE GAME", "#000000", "#FFFFFF");
        drawText(new Position(1,19),"5.HAVE FUN ", "#000000", "#FFFFFF");
        drawText(new Position(12,19),"n", "#000000", "#FFDB58");

        drawButtons(buttons, new ButtonViewer());
        gui.refresh();
}
    private void drawText(Position position, String text, String backColor, String textColor) {
        gui.drawTitle(position, text, backColor, textColor);
    }

}


