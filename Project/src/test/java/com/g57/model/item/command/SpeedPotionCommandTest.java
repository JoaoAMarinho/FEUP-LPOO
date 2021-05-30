package com.g57.model.item.command;

import com.g57.controller.PlayerController;
import com.g57.model.element.Player;
import com.g57.model.element.button.Button;
import com.g57.model.item.Gun;
import com.g57.model.item.Item;
import com.g57.model.item.Potion;
import com.g57.model.item.SpeedPotion;
import com.g57.model.item.command.buyCommand.GunCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpeedPotionCommandTest {
    private SpeedPotionCommand speedPotionCommand;
    private Potion item;
    private Button button;
    Player player;

    @BeforeEach
    void setUp() {
        this.item = new SpeedPotion("#FFFFFF");
        this.speedPotionCommand = new SpeedPotionCommand(item);
        this.player = Mockito.mock(Player.class);
        Map<Item, Integer> potionList = new HashMap<>();
        potionList.put(item,2);
        PlayerController playerController = new PlayerController(player);
        speedPotionCommand.setPlayerController(playerController);
        Mockito.when(player.getPotionList()).thenReturn(potionList);
    }

    @Test
    void execute() {
        speedPotionCommand.execute();
        Mockito.verify(player,Mockito.times(1)).changeSpeed(item.getIncreaser());
    }

    @Test
    void undo() {
        speedPotionCommand.execute();
        speedPotionCommand.undo();
        Mockito.verify(player,Mockito.times(1)).setDefaultColor();
    }

}
