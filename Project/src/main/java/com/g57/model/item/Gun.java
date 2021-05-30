package com.g57.model.item;

import java.util.ArrayList;
import java.util.List;

public class Gun extends Item {
    private int damage;
    private int speed;
    private int level;
    private final double range;
    private final int maxLevel;

    public Gun(int damage, double range, int speed, String color, int maxLevel, int level, int price) {
        super(price, color);
        this.damage = damage;
        this.range = range;
        this.speed = speed;
        this.maxLevel = maxLevel;
        this.level = level;
    }

    public int getDamage() {
        return damage;
    }
    public int getSpeed() {
        return speed;
    }
    public String getColor() {
        return color;
    }
    public double getRange() {
        return range;
    }
    public int getLevel() { return level; }

    public void increaseSpeed() {
        this.speed++;
    }
    public void decreaseSpeed() {
        this.speed--;
    }

    public void increaseDamage() {
        this.damage++;
    }
    public void decreaseDamage() {
        this.damage--;
    }

    public boolean increaseLevel() {
        if(this.level == this.maxLevel) return false;
        this.level++;
        return true;
    }

    public void decreaseLevel() {
        this.level--;
    }

    public void loadDamage(int value) {
        this.damage *= value;
    }
    public void loadSpeed(double value) {
        this.speed *= value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gun gun = (Gun) o;
        return this.range == gun.range && this.damage == gun.damage && this.speed == gun.speed;
    }

    @Override
    public List<String> getCharacteristics() {
        List<String> characteristics = new ArrayList<>();

        characteristics.add("DAMAGE " + damage);

        if (speed >= 10) characteristics.add("SPEED " + speed);
        else characteristics.add("SPEED  " + speed);

        if (range >= 10) characteristics.add("RANGE " + (int)range);
        else characteristics.add("RANGE  " + (int)range);

        if (price >= 10) characteristics.add("     " + price + "b");
        else characteristics.add("      " + price + "b");

        return characteristics;
    }
}
