package com.g57.model.item.command;

import com.g57.controller.PlayerController;
import com.g57.model.item.Item;
import com.g57.model.item.Potion;

public abstract class ConsumePotionCommand implements Command {
    protected final Item item;
    protected boolean executed;
    protected PlayerController playerController;
    protected long lastCall=0;

    public ConsumePotionCommand(Item item) {
        this.item=item;
        this.executed=false;
        this.playerController=null;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public Boolean timeOut(){
        Potion potion = (Potion) item;
        return ((System.currentTimeMillis() - potion.getStartTime())) >= potion.getDuration();
    }
}
