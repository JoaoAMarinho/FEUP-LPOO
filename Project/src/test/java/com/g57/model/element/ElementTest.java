package com.g57.model.element;

import com.g57.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElementTest {
    Element element;

    @BeforeEach
    void setUp() {
        element = Mockito.mock(Element.class);

        Mockito.doCallRealMethod().when(element).setPosition(Mockito.any());
        Mockito.doCallRealMethod().when(element).getPosition();

        Mockito.doCallRealMethod().when(element).setColor(Mockito.any());
        Mockito.doCallRealMethod().when(element).getColor();
    }

    @Test
    void position() {
        element.setPosition(new Position(6, 6));
        assertEquals(element.position, new Position(6, 6));
    }

    @Test
    void color() {
        element.setColor("#456789");
        assertEquals(element.color, "#456789");
    }
}
