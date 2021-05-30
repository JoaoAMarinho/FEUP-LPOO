package com.g57.model.item.command.buyCommand;

import com.g57.model.item.Item;

public class PotionCommand extends BuyCommand {

    public PotionCommand(Item item ) {
        super(item);
    }

    @Override
    public boolean execute() {
        if (!playerController.canBuy(item.getPrice())) return false;
        playerController.updateBudget(-item.getPrice());
        playerController.addItemToPlayer(item);
        return true;
    }

    @Override
    public void undo() {
        playerController.removePotion(item);
        playerController.updateBudget(item.getPrice());
    }
}
