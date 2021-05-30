package com.g57.model.item.command.buyCommand;

import com.g57.controller.PlayerController;
import com.g57.model.item.Item;
import com.g57.model.item.command.Command;

public abstract class BuyCommand implements Command {
    protected Item item;
    protected PlayerController playerController;

    protected BuyCommand(Item item) {
        this.item = item;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
