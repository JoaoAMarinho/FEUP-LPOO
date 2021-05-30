package com.g57.viewer.state;

import com.g57.gui.GUI;
import com.g57.model.element.Player;
import com.g57.model.element.button.Button;

import java.io.IOException;
import java.util.List;

public class PlayingViewer extends StateViewer{
    private final Player player;

    public PlayingViewer(GUI gui, List<Button> buttons, Player player) {
        super(gui, buttons);
        this.player = player;
    }

    @Override
    public void draw() throws IOException {
        gui.drawInfo(player.getBudget(), player.getEnergy(),player.getPotionList());
        gui.refresh();
    }
}
