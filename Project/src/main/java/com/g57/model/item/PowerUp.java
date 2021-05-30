package com.g57.model.item;

public class PowerUp extends Item {
    public PowerUp(int price, String color) {
        super(price, color);
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }
}
