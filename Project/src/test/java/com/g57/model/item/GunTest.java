package com.g57.model.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GunTest {
    private Gun gun;
    private List<String> characteristics;

    @BeforeEach
    void setUp() {
        gun = new Gun(1, 2.0, 3, "#123456", 4, 1, 5);
        characteristics = Arrays.asList("DAMAGE 1", "SPEED  3", "RANGE  2", "      5b");
    }

    @Test
    void getDamage() {
        assertEquals(gun.getDamage(), 1);
    }

    @Test
    void getRange() {
        assertEquals(gun.getRange(), 2.0);
    }

    @Test
    void getSpeed() {
        assertEquals(gun.getSpeed(), 3);
    }

    @Test
    void getColor() {
        assertEquals(gun.getColor(), "#123456");
    }

    @Test
    void increaseSpeed() {
        int speed = gun.getSpeed();
        int times = 10;

        for(int i = 0; i < times; i++) {
            gun.increaseSpeed();
        }

        assertEquals(gun.getSpeed(), speed + times);
    }

    @Test
    void decreaseSpeed() {
        int speed = gun.getSpeed();
        int times = 10;

        for(int i = 0; i < times; i++) {
            gun.decreaseSpeed();
        }

        assertEquals(gun.getSpeed(), speed - times);
    }

    @Test
    void increaseDamage() {
        int damage = gun.getDamage();
        int times = 10;

        for(int i = 0; i < times; i++) {
            gun.increaseDamage();
        }

        assertEquals(gun.getDamage(), damage + times);
    }

    @Test
    void decreaseDamage() {
        int damage = gun.getDamage();
        int times = 10;

        for(int i = 0; i < times; i++) {
            gun.decreaseDamage();
        }

        assertEquals(gun.getDamage(), damage - times);
    }

    @Test
    void increaseLevel() {
        for(int i = 0; i < 3; i++) {
            assertTrue(gun.increaseLevel());
        }

        assertFalse(gun.increaseLevel());
    }

    @Test
    void decreaseLevel() {
        int initLevel = gun.getLevel();

        for(int i = 0; i < 3; i++) {
            gun.decreaseLevel();
        }

        assertEquals(gun.getLevel(), initLevel - 3);
    }

    @Test
    void loadDamage() {
        gun.loadDamage(3);
        assertEquals(gun.getDamage(), 3);
        gun.loadDamage(2);
        assertEquals(gun.getDamage(), 6);
    }

    @Test
    void loadSpeed() {
        gun.loadSpeed(3);
        assertEquals(gun.getSpeed(), 9);
    }

    @Test
    void getCharacteristics() {
        List<String> gunCharacteristics = gun.getCharacteristics();
        for(int i = 0; i < characteristics.size(); i++) {
            assertEquals(characteristics.get(i), gunCharacteristics.get(i));
        }
    }

}
