package com.g57.model.element;

import com.g57.model.Position;
import com.g57.model.item.EnergyPotion;
import com.g57.model.item.Gun;
import com.g57.model.item.Item;
import com.g57.model.item.SpeedPotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class PlayerTest {
    private Player player;
    private Gun playerGun;

    @BeforeEach
    void setUp() {
        playerGun = Mockito.mock(Gun.class);
        player = new Player(new Position(6, 6), Arrays.asList("#FFFFFF", "#000000"), playerGun, 10);
    }

    @Test
    void updateBudget() {
        assertEquals(player.getBudget(), 4);
        player.updateBudget(+3);
        assertEquals(player.getBudget(), 7);
        player.updateBudget(-2);
        assertEquals(player.getBudget(), 5);
    }

    @Test
    void multiplyBudget() {
        player.multiplyBudget(0.5);
        assertEquals(player.getBudget(), 2);
        player.multiplyBudget(2);
        assertEquals(player.getBudget(), 4);

        player.updateBudget(+1);
        player.multiplyBudget(0.5);
        assertEquals(player.getBudget(), 2);
        player.multiplyBudget(2);
        assertEquals(player.getBudget(), 4);
    }

    @Test
    void getGun() {
        assertEquals(player.getGun(), playerGun);
    }

    @Test
    void setGun() {
        Gun newGun = Mockito.mock(Gun.class);
        player.setGun(newGun);
        assertEquals(player.getGun(), newGun);
        assertNotEquals(player.getGun(), playerGun);
    }

    @Test
    void getEnergy() {
        assertEquals(player.getEnergy(), 10);
    }

    @Test
    void setEnergy() {
        player.setEnergy(20);
        assertEquals(player.getEnergy(), player.getEnergy());
        player.setEnergy(3);
        assertEquals(player.getEnergy(), 3);
    }

    @Test
    void getMaxEnergy() {
        assertEquals(player.getMaxEnergy(), player.getEnergy());
        player.setEnergy(3);
        assertEquals(player.getMaxEnergy(), 10);
    }

    @Test
    void changeEnergy() {
        player.changeEnergy(+3);
        assertEquals(player.getMaxEnergy(), player.getEnergy());
        player.changeEnergy(-2);
        assertEquals(player.getEnergy(), 8);
    }

    @Test
    void defaultColor() {
        player.setDefaultColor();
        assertEquals(player.getColor(), "#FFDB58");
    }

    @Test
    void getSpeed() {
        assertEquals(player.getSpeed(), 1);
    }

    @Test
    void changeSpeed() {
        int lastValue = 1;
        for(int i = 0; i < 10; i++) {
            player.changeSpeed(+i);
            assertEquals(player.getSpeed(), lastValue + i);
            lastValue = player.getSpeed();
        }

        for(int i = 0; i < 10; i++) {
            player.changeSpeed(-i);
            assertEquals(player.getSpeed(), lastValue - i);
            lastValue = player.getSpeed();
        }
    }

    @Test
    void getPotionList() {
        EnergyPotion eP = Mockito.mock(EnergyPotion.class);
        SpeedPotion sP = Mockito.mock(SpeedPotion.class);
        Map<Item, Integer> potionList = player.getPotionList();
        assertEquals(potionList.size(), 2);
        assertNull(potionList.get(eP));
        assertNull(potionList.get(sP));
    }

    @Test
    void addPotion() {
        EnergyPotion eP = Mockito.mock(EnergyPotion.class);
        SpeedPotion sP = Mockito.mock(SpeedPotion.class);

        player.addPotion(eP);
        player.addPotion(eP);
        assertEquals(player.getPotionList().get(eP), 2);
        assertNull(player.getPotionList().get(sP));

        player.addPotion(sP);
        assertEquals(player.getPotionList().get(sP), 1);
    }

    @Test
    void removePotion() {
        EnergyPotion eP = Mockito.mock(EnergyPotion.class);
        SpeedPotion sP = Mockito.mock(SpeedPotion.class);

        player.addPotion(eP);
        player.addPotion(eP);

        player.removePotion(eP);
        assertEquals(player.getPotionList().get(eP), 1);
    }

    @Test
    void potionColorEffect() {
        //TODO
    }


}
