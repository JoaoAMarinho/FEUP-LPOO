package com.g57.model.element.enemy;

import com.g57.model.Position;
import com.g57.model.item.Gun;

import java.util.Arrays;

public class BigEnemy extends Enemy {

    public BigEnemy(){
        super(Arrays.asList("#FF6961", "#A34741"));
        this.gun = new Gun(4, 5, 1, "#FF00FF", 4, 1, 15);
    }

    public BigEnemy(Position position) {
        super(Arrays.asList("#FF6961", "#A34741"));
        this.position = position;
        this.gun = new Gun(4, 5, 1, "#FF00FF", 4, 1, 15);
    }

}
