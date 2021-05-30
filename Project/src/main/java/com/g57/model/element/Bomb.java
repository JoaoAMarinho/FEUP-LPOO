package com.g57.model.element;

import com.g57.model.Position;

public class Bomb extends Element{
    private final int damage;
    private boolean active;
    private long activeTime;

    public Bomb(Position position, String color, int damage) {
        super(position, color);
        this.damage = damage;
        this.active = false;
        this.activeTime = 0;
    }

    public void setActive(boolean active) {

        this.active = active;
        this.color = "#FF6D0B";
    }

    public long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(long activeTime) {
        this.activeTime = activeTime;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isActive() {
        return active;
    }
}
