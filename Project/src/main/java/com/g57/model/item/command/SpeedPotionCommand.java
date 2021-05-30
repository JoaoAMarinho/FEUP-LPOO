package com.g57.model.item.command;

import com.g57.model.item.Item;
import com.g57.model.item.SpeedPotion;

public class SpeedPotionCommand extends ConsumePotionCommand {
    private boolean executed = false;

    public SpeedPotionCommand(Item item) {
        super(item);
    }

    @Override
    public boolean execute() {
        if (executed){
            playerController.potionColorEffect();
            if (!timeOut()) return true;

            undo();
            return false;
        }
        if (!playerController.hasItem(item))  return false;
        playerController.removePotion(item);
        changeValues();
        executed = true;
        return true;
    }

    @Override
    public void undo() {
        if (!executed) return;
        playerController.setDefaultColor();
        SpeedPotion potion = (SpeedPotion) item;
        playerController.changeSpeed(-potion.getIncreaser());
        this.executed = false;
    }

    private void changeValues(){
        SpeedPotion potion = (SpeedPotion) item;
        potion.setStartTime(System.currentTimeMillis());
        playerController.changeSpeed(potion.getIncreaser());
    }

}
