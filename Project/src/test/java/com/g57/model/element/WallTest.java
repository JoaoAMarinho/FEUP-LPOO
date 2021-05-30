package com.g57.model.element;

import com.g57.model.Position;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class WallTest {

    @Test
    void equals() {
        Wall wall1 = new Wall(new Position(0, 10), "#456789");
        Wall wall2 = new Wall(new Position(0, 10), "#456789");
        Wall wall3 = new Wall(new Position(0, 10), "#456781");
        Wall wall4 = new Wall(new Position(10, 0), "#456789");

        assertEquals(wall1, wall2);
        assertEquals(wall1, wall3);
        assertNotEquals(wall1, wall4);
    }

}
