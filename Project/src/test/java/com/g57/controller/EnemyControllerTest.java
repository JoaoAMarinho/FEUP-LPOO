package com.g57.controller;

import com.g57.Game;
import com.g57.gui.GUI;
import com.g57.gui.LanternaGUI;
import com.g57.model.Position;
import com.g57.model.battlefield.Battlefield;
import com.g57.model.element.Bomb;
import com.g57.model.element.Player;
import com.g57.model.element.Shop;
import com.g57.model.element.bullet.Bullet;
import com.g57.model.element.bullet.BulletPool;
import com.g57.model.element.enemy.BigEnemy;
import com.g57.model.element.enemy.Enemy;
import com.g57.model.element.enemy.SmallEnemy;
import com.g57.state.PlayingState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnemyControllerTest {
    EnemyController enemyController;
    Battlefield battlefield;
    BattlefieldController battlefieldController;

    @BeforeEach
    void setUp() throws IOException {
        this.battlefield = new Battlefield();
        Game game = Mockito.mock(Game.class);
        GUI gui = Mockito.mock(LanternaGUI.class);
        this.battlefieldController = new BattlefieldController(new PlayingState(game,gui,1), gui, battlefield);
        this.enemyController = new EnemyController(battlefield,battlefieldController);
    }
    @Test
    void step() {
    }

    @Test
    void shoot() {
        Enemy enemy = new BigEnemy();
        enemy.getGun().increaseSpeed();

        BulletPool bulletPool = new BulletPool();
        battlefieldController.setBulletPool(bulletPool);

        Bullet bullet = new Bullet(1,1,"#FFFFFF", true);
        bulletPool.insertBullet(bullet);

        Position position = new Position(4,6);
        enemyController.shoot(enemy,position);
        Assertions.assertArrayEquals(new List[]{Arrays.asList((int)enemy.getGun().getSpeed(), false)}, new List[]{Arrays.asList((int)bullet.getSpeed(), bullet.isFiredByPlayer())});
        Bullet bulletBattlefield=  battlefield.getBullets().get(0);
        Assertions.assertEquals(bullet, bulletBattlefield);
    }

    @Test
    void shouldShoot() {
        Position position = new Position(4,5);
        double range = 10;
        Player player = new Player(new Position(5,6),Arrays.asList(), null,10);
        battlefield.setPlayer(player);

        Assertions.assertTrue(enemyController.shouldShoot(position,range));
        battlefield.getPlayer().setPosition(new Position(20,20));
        Assertions.assertFalse(enemyController.shouldShoot(position,range));
    }

    @Test
    void verifyPosition() {
        Game game = Mockito.mock(Game.class);
        Mockito.when(game.getHeight()).thenReturn(11);
        Mockito.when(game.getWidth()).thenReturn(11);
        battlefield.setWalls(Collections.emptyList());
        battlefield.setPortals(Collections.emptyList());
        battlefield.setObstacleS(Collections.emptyList());
        battlefield.setBombs(Collections.emptyList());
        battlefield.setEnemies(Collections.singletonList(new BigEnemy()));
        battlefield.setShop(new Shop(new Position(0,0), null));


        Position position = new Position(10,10);
        Assertions.assertTrue(enemyController.verifyPosition(game,position));
        battlefield.setBombs(Collections.singletonList(new Bomb(position, "", 11)));
        Assertions.assertFalse(enemyController.verifyPosition(game,position));

        position = new Position(30,30);
        Assertions.assertFalse(enemyController.verifyPosition(game,position));
    }

    @Test
    void bulletConfig() {
        Bullet bullet = new Bullet(10, 12, "", true);
        Enemy enemy = new SmallEnemy();
        enemy.getGun().increaseSpeed(); enemy.getGun().increaseSpeed(); enemy.getGun().increaseSpeed();
        enemy.setPosition(new Position(0,0));
        enemy.getGun().increaseDamage(); enemy.getGun().increaseDamage();

        enemyController.bulletConfig(enemy,bullet);

        Assertions.assertArrayEquals(new List[]{Arrays.asList(enemy.getGun().getSpeed(), enemy.getPosition().getX(), enemy.getPosition().getY(), enemy.getGun().getDamage(), false)}, new List[]{Arrays.asList((int)bullet.getSpeed(), (int)bullet.getX(), (int)bullet.getY(), bullet.getDamage(), bullet.isFiredByPlayer())});
    }
}