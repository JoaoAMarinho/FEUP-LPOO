package com.g57.model.item.command.buyCommand;

import com.g57.model.item.Item;

public class PowerUpDamageCommand extends BuyCommand {
    public PowerUpDamageCommand(Item item){
        super(item);
    }

    @Override
    public boolean execute() {
        if (!playerController.canBuy(item.getPrice())) return false;
        if(!playerController.canUpGunLevel()) return false;

        playerController.increaseGunDamage();
        playerController.updateBudget(-item.getPrice());
        return true;
    }

    @Override
    public void undo() {
        playerController.reduceGunLevel();
        playerController.decreaseGunDamage();
        playerController.updateBudget(item.getPrice());
    }
}
