package com.g57.model.element.bullet;

import com.g57.model.Position;
import com.g57.model.element.Element;

import java.util.Objects;

public class Bullet extends Element {
    private double x;
    private double y;
    private int speed;
    private int damage;
    private double range;
    private double degree;
    private boolean firedByPlayer;

    public Bullet( double x, double y, String color, boolean firedByPlayer) {
        super(new Position((int)x, (int)y), color);
        this.x=x;
        this.y=y;
        this.firedByPlayer = firedByPlayer;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setDegree(double degree) {
        this.degree = degree;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setRange(double range) { this.range = range; }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean updatePos(){
        this.x+=Math.cos(degree);
        this.y+=Math.sin(degree);
        setPosition(new Position((int)Math.floor(x), (int)Math.floor(y)));
        this.range--;
        return range > 0;
    }

    public int getIndexY() {
        return getIndex(y - Math.floor(y));
    }

    public int getIndexX() {
        return getIndex(x - Math.floor(x));
    }

    private int getIndex(double value) {
        if (value < 0.33) return 0;
        if (value < 0.66) return 1;
        return 2;
    }

    public int getDamage() {
        return damage;
    }

    public double getSpeed() {
        return speed;
    }

    public void setFiredByPlayer(boolean firedByPlayer) {
        this.firedByPlayer = firedByPlayer;
    }

    public boolean isFiredByPlayer() {
        return firedByPlayer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bullet bullet = (Bullet) o;
        return Double.compare(bullet.x, x) == 0 && Double.compare(bullet.y, y) == 0 && speed == bullet.speed && damage == bullet.damage && Double.compare(bullet.range, range) == 0 && Double.compare(bullet.degree, degree) == 0 && firedByPlayer == bullet.firedByPlayer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, speed, damage, range, degree, firedByPlayer);
    }
}
