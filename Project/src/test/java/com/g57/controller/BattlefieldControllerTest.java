package com.g57.controller;

import com.g57.Game;
import com.g57.gui.GUI;
import com.g57.gui.LanternaGUI;
import com.g57.model.Position;
import com.g57.model.battlefield.Battlefield;
import com.g57.model.battlefield.BattlefieldBuilder;
import com.g57.model.battlefield.BattlefieldLoader;
import com.g57.model.element.Player;
import com.g57.model.element.Shop;
import com.g57.model.element.bullet.Bullet;
import com.g57.model.element.bullet.BulletPool;
import com.g57.model.item.Gun;
import com.g57.state.GameState;
import com.g57.state.PlayingState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BattlefieldControllerTest {
    LanternaGUI gui;
    Battlefield battlefield;
    BulletPool bulletPool;
    BattlefieldController battlefieldController;

    @BeforeEach
    void setUp() throws IOException {
        this.gui = Mockito.mock(LanternaGUI.class);
        this.battlefield = new Battlefield();
        Player player = new Player(new Position(3,4),Arrays.asList(), new Gun(10,10,10,"",10,10,10),10);
        battlefield.setPlayer(player);

        this.bulletPool = new BulletPool();
        Game game = Mockito.mock(Game.class);
        this.battlefieldController = new BattlefieldController(new PlayingState(game,gui,1), gui, battlefield);
        battlefieldController.setBulletPool(bulletPool);
    }

    @Test
    void removeBullets() {
        Bullet bullet = new Bullet(10,10,"",false);
        battlefieldController.removeBullets(bullet);
        Bullet removedBullet = bulletPool.removeBullet();

        Assertions.assertEquals(removedBullet,bullet);

        List<Bullet> bullets = battlefield.getBullets();
        boolean hasBUllet = bullets.contains(bullet);
        Assertions.assertFalse(hasBUllet);
    }

    @Test
    void shoot() {
        Bullet bullet = new Bullet(1,1,"#FFFFFF", true);

        bulletPool.insertBullet(bullet);
        Position position = new Position(4,6);
        battlefieldController.shoot(position);
        Bullet bulletBattlefield= battlefield.getBullets().get(0);

        Assertions.assertEquals(bullet, bulletBattlefield);
    }

    @Test
    void nearShop() {
        battlefield.setShop(new Shop(new Position(3,4), ""));
        BattlefieldController battlefieldController = new BattlefieldController(null,gui, battlefield);

        assertTrue(battlefieldController.nearShop());
        battlefield.getPlayer().setPosition(new Position(25,25));
        assertFalse(battlefieldController.nearShop());
    }

    @Test
    void resetPlayer() {
        BattlefieldController battlefieldController = new BattlefieldController(null,gui,battlefield);
        PlayerController playerController = Mockito.mock(PlayerController.class);
        battlefieldController.setPlayerController(playerController);

        battlefieldController.resetPlayer();
        Mockito.verify(playerController,Mockito.times(1)).resetPlayer();
    }
}