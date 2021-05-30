package com.g57.model.item.command.buyCommand;

import com.g57.model.element.button.Button;
import com.g57.model.item.Gun;
import com.g57.model.item.Item;

import java.util.List;

public class GunCommand extends BuyCommand {
    private Gun oldGun;
    private List<Button> buttons;

    public GunCommand(Item item){
        super(item);
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    @Override
    public boolean execute() {
        if (!playerController.canBuy(item.getPrice())) return false;

        resetButtons();
        oldGun = playerController.getPlayer().getGun();
        playerController.changeGun((Gun)item);
        playerController.updateBudget(-item.getPrice());
        return true;
    }

    @Override
    public void undo() {
        resetButtons();
        if(oldGun==null) return;
        playerController.changeGun(oldGun);
        playerController.updateBudget(item.getPrice());
    }

    public void resetButtons(){
        for (Button button : buttons){
            if (button.isActive()) continue;
            button.getCommand().undo();
            button.activate();
        }
    }
}
