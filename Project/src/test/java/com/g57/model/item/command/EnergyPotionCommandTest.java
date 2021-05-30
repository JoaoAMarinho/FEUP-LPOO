package com.g57.model.item.command;

import com.g57.controller.PlayerController;
import com.g57.model.element.Player;
import com.g57.model.element.button.Button;
import com.g57.model.item.Item;
import com.g57.model.item.Potion;
import com.g57.model.item.SpeedPotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EnergyPotionCommandTest {
    private EnergyPotionCommand energyPotionCommand;
    private Potion item;
    private Button button;
    Player player;

    @BeforeEach
    void setUp() {
        this.item = new SpeedPotion("#FFFFFF");
        this.energyPotionCommand = new EnergyPotionCommand(item);
        this.player = Mockito.mock(Player.class);
        Map<Item, Integer> potionList = new HashMap<>();
        potionList.put(item,2);
        PlayerController playerController = new PlayerController(player);
        energyPotionCommand.setPlayerController(playerController);
        Mockito.when(player.getPotionList()).thenReturn(potionList);
    }

    @Test
    void execute() {
        energyPotionCommand.execute();
        Mockito.verify(player,Mockito.times(1)).changeEnergy(item.getIncreaser());
    }

    @Test
    void undo() {
        energyPotionCommand.undo();
        Mockito.verify(player,Mockito.times(1)).setDefaultColor();
    }
}