package com.g57.model.element.enemy;

import com.g57.model.Position;
import com.g57.model.element.Element;
import com.g57.model.item.Gun;

import java.util.List;

public abstract class Enemy extends Element {
    protected int energy;
    protected int maxEnergy;
    protected int bounty;
    protected List<String> colors;
    protected int speed;
    protected Gun gun;
    protected double lastShot;

    public Enemy(List<String> colors) {
        super(new Position(0, 0), colors.get(colors.size() - 1));
        this.energy = 0;
        this.maxEnergy = 0;
        this.bounty = 0;
        this.colors = colors;
        this.speed = 0;
        this.lastShot = 0;
    }

    public void setGun(Gun gun) {
        this.gun = gun;
    }

    public void setBounty(int bounty) {
        this.bounty = bounty;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getEnergy() {
        return energy;
    }
    public void setEnergy(int energy) {
        if(this.energy > this.maxEnergy) this.maxEnergy = this.energy;
        this.energy = energy;
    }

    //Controller
    public void decreaseEnergy(int damage) {
        this.energy -= damage;
        if(energy > 0) updateColor();
    }
    public void increaseEnergy() {
        this.energy++;
        updateColor();
    }

    public int getBounty() {
        return bounty;
    }

    public void updateColor() {
        int index = (this.energy >= (this.maxEnergy / 2)) ? 0 : 1;
        this.color = this.colors.get(index);
    }

    public void loadDamage(int value) {
        this.gun.loadDamage(value);
    }

    public void loadSpeed(int value) {
        this.gun.loadSpeed(value);
    }

    public int getSpeed() {
        return speed;
    }

    public double getLastShot() {
        return lastShot;
    }

    public void setLastShot(double lastShot) {
        this.lastShot = lastShot;
    }

    public Gun getGun() {
        return gun;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }
}
