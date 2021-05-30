package com.g57.model.item.command;

import com.g57.controller.PlayerController;
import com.g57.model.element.Player;
import com.g57.model.item.Potion;
import com.g57.model.item.SpeedPotion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ConsumePotionCommandTest {
    ConsumePotionCommand consumePotionCommand;
    Player player;
    Potion potion;

    @BeforeEach
    void setUp() {
        this.potion = new SpeedPotion("#FFFFFF");
        this.consumePotionCommand = new SpeedPotionCommand(potion);

        this.player = Mockito.mock(Player.class);
        consumePotionCommand.setPlayerController(new PlayerController(player));
    }

    @Test
    void timeOut() {
        potion.setStartTime(0);
        int duration = potion.getDuration();

        Assertions.assertEquals((System.currentTimeMillis()> duration),consumePotionCommand.timeOut());
    }
}