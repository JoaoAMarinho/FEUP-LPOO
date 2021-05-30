package com.g57.model.element.obstacle;

import com.g57.model.Position;
import com.g57.model.element.Element;

public class Obstacle extends Element {
    private int energy;

    public Obstacle(Position position, String color, int energy) {
        super(position, color);
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }

    public void decreaseEnergy(int damage) {
        this.energy -= damage;
    }
}
