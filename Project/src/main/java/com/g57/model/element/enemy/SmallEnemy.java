package com.g57.model.element.enemy;

import com.g57.model.Position;
import com.g57.model.item.Gun;

import java.util.Arrays;

public class SmallEnemy extends Enemy {

    public SmallEnemy(){
        super(Arrays.asList("#FFB7AD", "#DC4A46"));
        this.gun = new Gun(1, 15, 1, "#FF00FF", 4, 1, 15);
    }
    public SmallEnemy(Position position) {
        super(Arrays.asList("#FFB7AD", "#DC4A46"));
        this.position = position;
        this.gun = new Gun(1, 15, 1, "#FF00FF", 4, 1, 15);
    }
}
