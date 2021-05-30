package com.g57.model.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerUpTest {
    PowerUp powerUp;

    @BeforeEach
    void setUp() {
        powerUp = new PowerUp(10, "#123456");
    }

    @Test
    void getColor() {
        assertEquals(powerUp.getColor(), "#123456");
    }

    @Test
    void getPrice() {
        assertEquals(powerUp.getPrice(), 10);
    }

    @Test
    void getCharacteristics() {
        assertEquals(powerUp.getCharacteristics(), Collections.singletonList("10b"));
    }
}
