package com.g57.controller;

import com.g57.gui.GUI;
import com.g57.model.Position;
import com.g57.model.battlefield.Battlefield;
import com.g57.model.battlefield.BattlefieldLoader;
import com.g57.model.element.Player;
import com.g57.model.element.bullet.Bullet;
import com.g57.model.item.EnergyPotion;
import com.g57.model.item.Gun;
import com.g57.model.item.Item;
import com.g57.model.item.SpeedPotion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControllerTest {
    private PlayerController playerController;
    private Player player;

    @BeforeEach
    void setUp() throws IOException {
        Battlefield battlefield = new BattlefieldLoader(1).createBattlefield(25, 25);
        this.player = battlefield.getPlayer();
        this.playerController = new PlayerController(battlefield.getPlayer());
    }
    @Test
    void movePlayerLeft() {
        Position previousPosition = playerController.getPlayer().getPosition();

        Position position=playerController.movePlayerLeft();
        playerController.movePlayer(position);
        assertEquals(new Position(previousPosition.getX()-1,previousPosition.getY()),position);
    }

    @Test
    void movePlayerRight() {
        Position previousPosition = playerController.getPlayer().getPosition();
        Position position=playerController.movePlayerRight();
        playerController.movePlayer(position);
        assertEquals(new Position(previousPosition.getX() + 1, previousPosition.getY()),player.getPosition());
    }

    @Test
    void movePlayerUp() {
        Position previousPosition = playerController.getPlayer().getPosition();
        Position position=playerController.movePlayerUp();
        playerController.movePlayer(position);
        assertEquals(new Position(previousPosition.getX(), previousPosition.getY() - 1),player.getPosition());
    }

    @Test
    void movePlayerDown() {
        Position previousPosition = playerController.getPlayer().getPosition();
        Position position=playerController.movePlayerDown();
        playerController.movePlayer(position);
        assertEquals(new Position(previousPosition.getX(), previousPosition.getY() + 1),player.getPosition());
    }

    @Test
    void movePlayer() {
        playerController.movePlayer(new Position(3,3));
        assertEquals(new Position(3,3),player.getPosition());
    }

    @Test
    void doAction() {
        Assertions.assertArrayEquals(new List[]{Arrays.asList(playerController.movePlayerUp(),
                playerController.movePlayerDown(),
                playerController.movePlayerLeft(),
                playerController.movePlayerRight())},
                new List[]{Arrays.asList(playerController.doAction(GUI.ACTION.UP),
                        playerController.doAction(GUI.ACTION.DOWN),
                        playerController.doAction(GUI.ACTION.LEFT),
                        playerController.doAction(GUI.ACTION.RIGHT))});
    }

    @Test
    void bulletConfig() {
        Bullet bullet = new Bullet(10,10,"",false);
        playerController.bulletConfig(bullet);

        Assertions.assertArrayEquals(new List[]{Arrays.asList(player.getPosition().getX(),
                        player.getPosition().getY(),
                        player.getGun().getSpeed(),
                        true)}, new List[]{List.<java.io.Serializable>of(
                                (int)bullet.getX(),
                                (int)bullet.getY(),
                                (int)bullet.getSpeed(),
                                bullet.isFiredByPlayer())});

    }

    @Test
    void canBuy() {
        int budget = player.getBudget();
        Assertions.assertTrue(playerController.canBuy(budget));
        player.updateBudget(-2);
        Assertions.assertFalse(playerController.canBuy(budget));
    }

    @Test
    void canUpGunLevel() {
        player.setGun(new Gun(10,10,10,"",10,10,10));
        Assertions.assertFalse(playerController.canUpGunLevel());

        player.setGun(new Gun(10,10,10,"",15,10,10));
        Assertions.assertTrue(playerController.canUpGunLevel());
    }

    @Test
    void changeGun() {
        Gun gun = new Gun(10,10,10,"",10,10,10);
        playerController.changeGun(gun);

        Assertions.assertEquals(gun,player.getGun());
    }

    @Test
    void increaseGunSpeed() {
        int speed = player.getGun().getSpeed();
        playerController.increaseGunSpeed();

        Assertions.assertEquals(speed+1,player.getGun().getSpeed());
    }

    @Test
    void decreaseGunSpeed() {
        int speed = player.getGun().getSpeed();
        playerController.decreaseGunSpeed();

        Assertions.assertEquals(speed-1,player.getGun().getSpeed());
    }

    @Test
    void increaseGunDamage() {
        int damage = player.getGun().getDamage();
        playerController.increaseGunDamage();

        Assertions.assertEquals(damage + 1,player.getGun().getDamage());
    }

    @Test
    void decreaseGunDamage() {
        int damage = player.getGun().getDamage();
        playerController.decreaseGunDamage();

        Assertions.assertEquals(damage - 1,player.getGun().getDamage());
    }

    @Test
    void addItemToPlayer() {
        Item item = new EnergyPotion("");
        playerController.addItemToPlayer(item);
        Assertions.assertTrue(playerController.hasItem(item));

        item = new SpeedPotion("");
        playerController.addItemToPlayer(item);
        Assertions.assertTrue(playerController.hasItem(item));
    }

    @Test
    void updateBudget() {
        int budget = player.getBudget();
        playerController.updateBudget(30);

        Assertions.assertEquals(budget + 30, player.getBudget());
    }

    @Test
    void removePotion() {
        Item item = new EnergyPotion("");
        playerController.addItemToPlayer(item);
        Assertions.assertTrue(playerController.hasItem(item));

        playerController.removePotion(item);
        Assertions.assertFalse(playerController.hasItem(item));
    }

    @Test
    void changeEnergy() {
        int energy = player.getEnergy();
        playerController.changeEnergy(30);

        Assertions.assertEquals(Math.min(energy + 30, player.getMaxEnergy()), player.getEnergy());

        energy = player.getEnergy();
        playerController.changeEnergy(- 20);

        Assertions.assertEquals(energy - 20, player.getEnergy());
    }

    @Test
    void changeSpeed() {
        int speed = player.getSpeed();
        playerController.changeSpeed(30);

        Assertions.assertEquals(speed + 30, player.getSpeed());

        speed = player.getSpeed();
        playerController.changeSpeed(- 20);

        Assertions.assertEquals(speed - 20, player.getSpeed());
    }

    @Test
    void hasItem() {
        Item item = new EnergyPotion("");
        playerController.addItemToPlayer(item);

        Assertions.assertTrue(playerController.hasItem(item));
    }

    @Test
    void resetPlayer() {
        playerController.resetPlayer();

        Assertions.assertArrayEquals(new List[]{Arrays.asList(new Position(6, 6), player.getMaxEnergy())}, new List[]{Arrays.asList(player.getPosition(), player.getEnergy())});
    }
}