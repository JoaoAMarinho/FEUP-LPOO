package com.g57.model.item.command.buyCommand;

import com.g57.model.item.Item;

public class PowerUpSpeedCommand extends BuyCommand {
    public PowerUpSpeedCommand(Item item){
        super(item);
    }

    @Override
    public boolean execute() {
        if (!playerController.canBuy(item.getPrice())) return false;
        if(!playerController.canUpGunLevel()) return false;

        playerController.updateBudget(-item.getPrice());
        playerController.increaseGunSpeed();
        return true;
    }

    @Override
    public void undo() {
        playerController.reduceGunLevel();
        playerController.decreaseGunSpeed();
        playerController.updateBudget(item.getPrice());
    }
}

