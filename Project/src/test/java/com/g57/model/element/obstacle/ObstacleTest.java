package com.g57.model.element.obstacle;

import com.g57.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObstacleTest {
    private Obstacle obstacle;

    @BeforeEach
    void setUp(){
        obstacle = new Obstacle(new Position(4,4),"#FFFFFF", 10);
    }
    @Test
    void getEnergy() {
        assertEquals(10,obstacle.getEnergy());
    }

    @Test
    void decreaseEnergy() {
        obstacle.decreaseEnergy(5);
        assertEquals(5,obstacle.getEnergy());
    }
}