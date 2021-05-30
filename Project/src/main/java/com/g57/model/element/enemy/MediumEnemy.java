package com.g57.model.element.enemy;

import com.g57.model.Position;
import com.g57.model.item.Gun;

import java.util.Arrays;

public class MediumEnemy extends Enemy {

    public MediumEnemy(){
        super(Arrays.asList("#EF7771", "#FF261B"));
        this.gun = new Gun(3, 10, 1, "#FF00FF", 4, 1, 15);
    }
    public MediumEnemy(Position position) {
        super(Arrays.asList("#EF7771", "#FF261B"));
        this.gun = new Gun(3, 10, 1, "#FF00FF", 4, 1, 15);
    }

}
