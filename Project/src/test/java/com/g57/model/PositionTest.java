package com.g57.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    private Position position;

    @BeforeEach
    void setUp() {
        position = new Position(10, 10);
    }

    @Test
    void getUp() {
        assertEquals(5, position.getUp(5).getY());
    }

    @Test
    void getDown() {
        assertEquals(11, position.getDown(1).getY());
    }

    @Test
    void getLeft() {
        assertEquals(8, position.getLeft(2).getX());
    }

    @Test
    void getRight() {
        assertEquals(11, position.getRight(1).getX());
    }

    @Test
    void positionEquals() {
        assertEquals(new Position(10, 10), position);
    }

    @Test
    void getRandomNeighbour() {
        List<Position> possiblePositions = Arrays.asList(new Position(10, 9),
                                                         new Position(9, 10),
                                                         new Position(11, 10),
                                                         new Position(10, 11));

        Position randomPosition = position.getRandomNeighbour(1);

        assertTrue(possiblePositions.contains(randomPosition));
    }
}