package com.g57.model.element;

import com.g57.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PortalTest {
    Position pos1, pos2;
    Portal p1, p2;

    @BeforeEach
    void setUp() {
        pos1 = new Position(0, 20);
        pos2 = new Position(20, 0);
        List<Portal> portals = Portal.createPortals(pos1, pos2);
        p1 = portals.get(0);
        p2 = portals.get(1);
    }

    @Test
    void getDest() {
        assertEquals(p1.getDest(), p2);
        assertEquals(p2.getDest(), p1);
    }

    @Test
    void setDest() {
        p1.setDest(p1);
        assertNotEquals(p1.getDest(), p2);
        assertEquals(p1.getDest(), p1);
    }

    @Test
    void createPortals() {
        assertEquals(p1.getPosition(), p2.getDest().getPosition());
        assertEquals(p2.getPosition(), p1.getDest().getPosition());
    }

    @Test
    void getLeavePos() {
        Position p1LeavePos = p1.getLeavePos(25, 25);
        assertEquals(p1LeavePos, new Position(1, 20));
        Position p2LeavePos = p2.getLeavePos(25, 25);
        assertEquals(p2LeavePos, new Position(20, 1));

        List<Portal> portals = Portal.createPortals(new Position(10, 24), new Position(24, 10));
        Portal p3 = portals.get(0);
        Portal p4 = portals.get(1);

        Position p3LeavePos = p3.getLeavePos(25, 25);
        assertEquals(p3LeavePos, new Position(10, 23));
        Position p4LeavePos = p4.getLeavePos(25, 25);
        assertEquals(p4LeavePos, new Position(23, 10));
    }

}
