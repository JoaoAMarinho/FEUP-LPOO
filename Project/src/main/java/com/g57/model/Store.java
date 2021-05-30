package com.g57.model;

import com.g57.gui.GUI;
import com.g57.model.element.button.Button;
import com.g57.model.item.*;
import com.g57.model.item.command.DisplayInfoCommand;
import com.g57.model.item.command.SwapCommand;
import com.g57.model.item.command.buyCommand.GunCommand;
import com.g57.model.item.command.buyCommand.PotionCommand;
import com.g57.model.item.command.buyCommand.PowerUpDamageCommand;
import com.g57.model.item.command.buyCommand.PowerUpSpeedCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Store {
    private final List<Button> buttons = new ArrayList<>();
    private final Button displayGunInfoButton;

    public Store(GUI gui) {
        Item energyPotion = new EnergyPotion("#FFFFFF");
        Item speedPotion = new SpeedPotion("#FFFFFF");
        Item powerUpDamage= new PowerUp(1, "#FFFFFF");
        Item powerUpSpeed = new PowerUp(1, "#FFFFFF");
        Item gun1 = new Gun(2, 2, 2,"#FFFFFF", 3, 1, 1);
        Item gun2 = new Gun(1, 8, 5,"#FF0000", 3, 1, 1);
        List<String> colors = Arrays.asList("#FFFFFF", "#FF0000");

        this.buttons.add(new Button(new Position(13, 7), new Position(14, 8),"l",3,4, new PotionCommand(energyPotion), colors));
        this.buttons.add(new Button(new Position(17, 7), new Position(18, 8),"j", 3,4, new PotionCommand(speedPotion), colors));
        this.buttons.add(new Button(new Position(13, 12), new Position(14, 13),"k", 3, 4, new PowerUpDamageCommand(powerUpDamage), colors));
        this.buttons.add(new Button(new Position(17, 12), new Position(18, 13),"m", 3,4, new PowerUpSpeedCommand(powerUpSpeed), colors));

        GunCommand gunCommand = new GunCommand(gun1);
        gunCommand.setButtons(Arrays.asList(buttons.get(2), buttons.get(3)));
        Button button = new Button(new Position(4, 7), new Position(8, 8),"`", 8, 9, gunCommand, colors);
        this.buttons.add(new Button(new Position(11,15), ">", new SwapCommand(gun2,button), colors));
        this.buttons.add(button);
        this.displayGunInfoButton = new Button(new Position(4,17), "w", new DisplayInfoCommand(gui, new Position(4,18)) ,Arrays.asList("#000000", "#FFFF00"));
        this.displayGunInfoButton.setBgColor("#BBBBBB");
    }

    public Button getDisplayGunInfoButton() {
        return displayGunInfoButton;
    }

    public List<Button> getButtons() {
        return buttons;
    }
}
