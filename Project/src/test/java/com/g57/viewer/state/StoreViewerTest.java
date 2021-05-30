package com.g57.viewer.state;

import com.g57.gui.GUI;
import com.g57.gui.LanternaGUI;
import com.g57.model.Position;
import com.g57.model.element.Player;
import com.g57.model.element.button.Button;
import com.g57.model.item.Gun;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StoreViewerTest {
    StoreViewer storeViewer;
    List<Button> buttons;
    GUI gui;
    Player player;

    @BeforeEach
    void setUp() {
        List<String> colors = Collections.singletonList("#FFFFFF");
        this.buttons = Arrays.asList(new Button(new Position(1,2), null,colors), new Button(new Position(3,4),null,colors));
        this.gui = Mockito.mock(LanternaGUI.class);
        this.player = new Player(new Position(1,1),colors,null,10);
        this.storeViewer = new StoreViewer(gui,buttons,player);
    }
    @Test
    void draw() throws IOException {

        storeViewer.draw();

        for (Button button : buttons)
            Mockito.verify(gui,Mockito.times(1)).drawButton(button.getPosition(),button.getTextPosition(),
                    button.getText(),button.getBgColor(), button.getColor(),button.getWidth(),button.getHeight());

        Mockito.verify(gui, Mockito.times(1)).drawTitle(new Position(11,4),"SHOP","#000000","#FFFFFF");
        Mockito.verify(gui, Mockito.times(1)).drawInfo(player.getBudget(),player.getEnergy(),player.getPotionList());
    }


}