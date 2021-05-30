package com.g57.model.element;

import com.g57.model.Position;

public class Wall extends Element {

    public Wall(Position position, String color) {
        super(position, color);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Wall)) return false;
        Wall wall = (Wall) obj;
        return wall.getPosition().equals(position);
    }
}
