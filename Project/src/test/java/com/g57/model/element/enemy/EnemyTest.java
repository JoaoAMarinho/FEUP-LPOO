package com.g57.model.element.enemy;

import com.g57.model.item.Gun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
    private Enemy enemy;

    @BeforeEach
    void setUp(){
        this.enemy = Mockito.mock(Enemy.class);
        Gun enemyGun = Mockito.mock(Gun.class);

        Mockito.doCallRealMethod().when(this.enemy).updateColor();
        Mockito.doCallRealMethod().when(this.enemy).getColor();
        Mockito.doCallRealMethod().when(this.enemy).setColors(Mockito.anyList());
        this.enemy.setColors(Arrays.asList("#FF1122", "#2211FF"));

        Mockito.doCallRealMethod().when(this.enemy).setGun(enemyGun);
        this.enemy.setGun(enemyGun);

        Mockito.doCallRealMethod().when(this.enemy).setEnergy(Mockito.anyInt());
        Mockito.doCallRealMethod().when(this.enemy).getEnergy();

        Mockito.doCallRealMethod().when(this.enemy).setBounty(Mockito.anyInt());
        Mockito.doCallRealMethod().when(this.enemy).getBounty();

        Mockito.doCallRealMethod().when(this.enemy).loadDamage(Mockito.anyInt());
        Mockito.doCallRealMethod().when(this.enemy).loadSpeed(Mockito.anyInt());

        Mockito.doCallRealMethod().when(this.enemy).setLastShot(Mockito.anyDouble());
        Mockito.doCallRealMethod().when(this.enemy).getLastShot();

        Mockito.doCallRealMethod().when(this.enemy).increaseEnergy();
        Mockito.doCallRealMethod().when(this.enemy).decreaseEnergy(Mockito.anyInt());

        Mockito.doCallRealMethod().when(this.enemy).setSpeed(Mockito.anyInt());
        Mockito.doCallRealMethod().when(this.enemy).getSpeed();

        Mockito.doCallRealMethod().when(this.enemy).loadDamage(Mockito.anyInt());
        Mockito.doCallRealMethod().when(this.enemy).loadSpeed(Mockito.anyInt());

    }

    @Test
    void setEnergy() {
        enemy.setEnergy(20);
        assertEquals(20,enemy.getEnergy());
    }

    @Test
    void decreaseEnergy() {
        int previousEnergy = enemy.getEnergy();
        int decreaser = 2;
        enemy.decreaseEnergy(decreaser);
        assertEquals(previousEnergy - decreaser,enemy.getEnergy());
    }

    @Test
    void increaseEnergy() {
        int previousEnergy = enemy.getEnergy();
        enemy.increaseEnergy();
        assertEquals(previousEnergy + 1,enemy.getEnergy());
    }

    @Test
    void setLastShot() {
        this.enemy.setLastShot(123);
        assertEquals(this.enemy.getLastShot(), 123);
    }

    @Test
    void energy() {
        int energy = 5;
        assertEquals(this.enemy.getEnergy(), 0);
        this.enemy.setEnergy(energy);
        assertEquals(this.enemy.getEnergy(), energy);
    }

    @Test
    void bounty() {
        int bounty = 10;
        assertEquals(this.enemy.getBounty(), 0);
        this.enemy.setBounty(bounty);
        assertEquals(this.enemy.getBounty(), bounty);
    }

    @Test
    void speed() {
        int speed = 7;
        assertEquals(this.enemy.getSpeed(), 0);
        this.enemy.setSpeed(speed);
        assertEquals(this.enemy.getSpeed(), speed);
    }

    @Test
    void loadDamage() {
        this.enemy.loadDamage(1);
        Mockito.verify(this.enemy.gun, Mockito.times(1)).loadDamage(1);
    }

    @Test
    void loadSpeed() {
        this.enemy.loadSpeed(1);
        Mockito.verify(this.enemy.gun, Mockito.times(1)).loadSpeed(1);
    }

    @Test
    void updateColor() {
        int energy = 10;
        this.enemy.setEnergy(energy);
        this.enemy.updateColor();
        assertEquals(this.enemy.getColor(), "#FF1122");
        this.enemy.setEnergy(energy / 4);
        this.enemy.updateColor();
        assertEquals(this.enemy.getColor(), "#2211FF");
    }
}