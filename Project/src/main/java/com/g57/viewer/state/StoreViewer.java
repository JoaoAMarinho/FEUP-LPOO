package com.g57.viewer.state;

import com.g57.gui.GUI;
import com.g57.model.Position;
import com.g57.model.element.Player;
import com.g57.model.element.button.Button;
import com.g57.model.item.command.buyCommand.BuyCommand;
import com.g57.viewer.element.ButtonViewer;

import java.io.IOException;
import java.util.List;

public class StoreViewer extends StateViewer {
    private final Player player;

    public StoreViewer(GUI gui, List< Button> buttons, Player player) {
        super(gui, buttons);
        this.player=player;
    }

    public void draw() throws IOException {
        //Draw menu background
        drawRectangle("#B8B8B8", 18,13, new Position(3,5));

        //Draw Shop Title
        drawText(new Position(11,4),"SHOP","#000000", "#FFFFFF");

        //Draw Buttons
        drawButtons(buttons, new ButtonViewer());
        drawCharacteristics();
        drawInfo();

        gui.refresh();
    }

    private void drawInfo() {
        drawRectangle("#000000", gui.getWidth(), 1, new Position(0, gui.getHeight()));
        gui.drawInfo(player.getBudget(), player.getEnergy(), player.getPotionList());
    }

    private void drawCharacteristics() {
        for (Button button : buttons){
            if (!button.isActive()) {
                if (!(button.getCommand() instanceof BuyCommand)) continue;
                drawUndo(button);
                continue;
            }

            int delta = 2;
            if (!(button.getCommand() instanceof BuyCommand)) continue;

            BuyCommand buyCommand = (BuyCommand) button.getCommand();
            for (String str : buyCommand.getItem().getCharacteristics()){
                Position bPos = button.getPosition();
                Position tPos = button.getTextPosition();
                drawText(new Position(bPos.getX(), tPos.getY()+delta), str, "#000000", "#FFFFFF");
                delta++;
            }
        }
    }

    private void drawUndo(Button storeButton) {
        Position bPos = storeButton.getPosition();
        Position tPos = storeButton.getTextPosition();
        drawText(new Position(bPos.getX() + storeButton.getWidth() - 1, tPos.getY()+2), "e", "#000000", "#FF0000");
    }

    private void drawText(Position position, String text, String backColor, String textColor) {
        gui.drawTitle(position, text, backColor, textColor);
    }
}
