package com.g57.viewer.element;

import com.g57.gui.GUI;
import com.g57.gui.LanternaGUI;
import com.g57.model.Position;
import com.g57.model.element.bullet.Bullet;
import com.g57.model.element.button.Button;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ButtonViewerTest {
    Button button;
    ButtonViewer buttonViewer;
    GUI gui;

    @BeforeEach
    void setUp() {
        Position position = new Position(3,5);
        this.button = new Button(position,position,"text",3,3,null, Arrays.asList("#FFFFFF"));
        this.buttonViewer= new ButtonViewer();
        this.gui = Mockito.mock(LanternaGUI.class);

    }

    @Test
    void drawElement() {
        buttonViewer.drawElement(button,gui);
        Mockito.verify(gui, Mockito.times(1)).drawButton(button.getPosition(),
                button.getTextPosition(),button.getText(),button.getBgColor(), button.getColor(), button.getWidth(),
                button.getHeight());
    }
}