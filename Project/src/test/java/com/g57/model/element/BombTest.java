package com.g57.model.element;

import com.g57.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BombTest {
    private Bomb bomb;

    @BeforeEach
    void setUp() {
        bomb = new Bomb(new Position(5, 5), "#FFFFFF", 3);
    }

    @Test
    void constructor() {
        assertFalse(bomb.isActive());
        assertEquals(0, bomb.getActiveTime());
        assertEquals(bomb.getDamage(), 3);
    }

    @Test
    void setActive() {
        bomb.setActive(true);
        assertTrue(bomb.isActive());
    }

    @Test
    void setActiveTime() {
        bomb.setActiveTime(123);
        assertEquals(bomb.getActiveTime(), 123);
    }
}
