package com.g57.model.item.command;

import com.g57.model.item.Item;
import com.g57.model.item.Potion;

public class EnergyPotionCommand extends ConsumePotionCommand {

    public EnergyPotionCommand(Item item) {
        super(item);
    }

    @Override
    public boolean execute() {
        if (executed){
            playerController.potionColorEffect();
            if (timeOut()){ undo(); return false; }
            if (canExecute()) changeValues();
            return true;
        }
        if (!playerController.hasItem(item))  return false;

        this.playerController.removePotion(item);
        changeValues();
        this.executed = true;
        Potion potion = (Potion)item;
        potion.setStartTime(System.currentTimeMillis());
        this.lastCall=potion.getStartTime();

        return true;
    }

    private boolean canExecute() {
        return System.currentTimeMillis()-lastCall >= 1000;
    }

    @Override
    public void undo() {
        executed = false;
        playerController.setDefaultColor();
    }

    private void changeValues(){
        Potion potion = (Potion)item;
        this.lastCall=System.currentTimeMillis();
        playerController.changeEnergy(potion.getIncreaser());
    }
}
