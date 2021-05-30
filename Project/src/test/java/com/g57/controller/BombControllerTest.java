package com.g57.controller;

import com.g57.model.battlefield.Battlefield;
import com.g57.model.battlefield.BattlefieldLoader;
import com.g57.model.element.Bomb;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BombControllerTest {
    Battlefield battlefield;
    BombController bombController;

    @BeforeEach
    void setUp() throws IOException {
        this.battlefield = new BattlefieldLoader(1).createBattlefield(20, 20);
        this.bombController = new BombController(battlefield);
    }

    @Test
    void step() {
        List<Bomb> bombs = battlefield.getBombs();

        for (Bomb b : bombs) {
            b.setActive(true);
            b.setActiveTime(0);
        }

        Assertions.assertEquals(Arrays.asList(), battlefield.getBombs());
    }
}