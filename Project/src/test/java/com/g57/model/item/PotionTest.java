package com.g57.model.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PotionTest {
    private EnergyPotion energyPotion;
    private SpeedPotion speedPotion;

    @BeforeEach
    void setUp() {
        energyPotion = new EnergyPotion("#000000");
        speedPotion = new SpeedPotion("#000000");

    }

    @Test
    void getDuration() {
        assertEquals(energyPotion.getDuration(), 5000);
        assertEquals(speedPotion.getDuration(), 10000);
    }

    @Test
    void getIncreaser() {
        assertEquals(energyPotion.getIncreaser(), 1);
        assertEquals(speedPotion.getIncreaser(), energyPotion.getIncreaser());
    }

    @Test
    void getPrice() {
        assertEquals(energyPotion.getPrice(), 4);
        assertEquals(speedPotion.getPrice(), 2);
    }

    @Test
    void getColor() {
        assertEquals(energyPotion.getColor(), "#000000");
        assertEquals(speedPotion.getColor(), "#000000");
    }

    @Test
    void startTime() {
        energyPotion.setStartTime(123);

        assertEquals(energyPotion.getStartTime(), 123);
    }

    @Test
    void equals() {
        EnergyPotion energyPotion1 = new EnergyPotion("#123456");

        assertEquals(energyPotion, energyPotion1);

        assertNotEquals(energyPotion, speedPotion);
    }

}
