package com.g57.model.battlefield;

import com.g57.model.Position;
import com.g57.model.element.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.security.cert.PolicyQualifierInfo;

import static org.junit.jupiter.api.Assertions.*;

class BattlefieldTest {
    private BattlefieldLoader bL;
    private Player player;

    @BeforeEach
    void setUp() {
        bL = Mockito.mock(BattlefieldLoader.class);
        player = Mockito.mock(Player.class);
    }

    @Test
    void getWalls() throws IOException {
        Battlefield b = new BattlefieldLoader(1).createBattlefield(20, 20);

        assertEquals(b.getWalls().size(), 76);
    }

    @Test
    void isEmpty() throws IOException {
        Battlefield b = new BattlefieldLoader(1).createBattlefield(20, 20);

        assertTrue(b.isEmpty(new Position(20, 20)));
        assertFalse(b.isEmpty(new Position(0, 0)));
    }
}