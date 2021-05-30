package com.g57.model.item.command;

import com.g57.controller.PlayerController;
import com.g57.gui.GUI;
import com.g57.model.Position;
import com.g57.viewer.GunInfoViewer;

public class DisplayInfoCommand implements Command {
    private final GunInfoViewer gunInfoViewer;

    public DisplayInfoCommand(GUI gui, Position position) {
        gunInfoViewer = new GunInfoViewer(gui, position);
    }

    @Override
    public boolean execute() {
        gunInfoViewer.draw();
        return true;
    }

    @Override
    public void undo() {
        gunInfoViewer.undo();
    }

    public void setPlayerController(PlayerController playerController) {
        gunInfoViewer.setPlayer(playerController.getPlayer());
    }
}
