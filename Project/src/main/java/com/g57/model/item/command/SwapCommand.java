package com.g57.model.item.command;

import com.g57.model.element.button.Button;
import com.g57.model.item.Item;
import com.g57.model.item.command.buyCommand.GunCommand;

public class SwapCommand implements Command {
    private final Button gunButton;
    private Item item;

    public SwapCommand(Item item, Button button) {
        this.gunButton = button;
        this.item = item;
    }

    @Override
    public boolean execute() {
        //Changes weapons from this to gunButton
        GunCommand gunCommand = (GunCommand) gunButton.getCommand();

        //Resets all buttons related to the gunButton
        undoGunButton();

        Item tmp = gunCommand.getItem();
        gunCommand.setItem(this.item);
        this.item=tmp;
        return true;
    }

    @Override
    public void undo() {}

    private void undoGunButton() {
        GunCommand gunCommand = (GunCommand) gunButton.getCommand();
        if (!gunButton.isActive()) {
            gunCommand.undo();
            gunButton.activate();
            return;
        }

        gunCommand.resetButtons();
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    public Item getItem() {
        return this.item;
    }
}
