package com.g57.model.battlefield;

import com.g57.gui.LanternaGUI;
import com.g57.model.Position;
import com.g57.model.element.Player;
import com.g57.model.item.Gun;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BattlefieldBuilderTest {
    BattlefieldBuilder battlefieldBuilder;

    @Test
    void createBattlefield() {
    }

    /*@Test
    void createPlayer() {
        Player player = battlefieldBuilder.createBattlefield(25,25).getPlayer();

        assertEquals(new Position(4,4), player.getPosition());
        assertEquals("#FFFFFF", player.getColor());
        assertEquals(1,player.getSpeed());
        assertEquals(3,player.getEnergy());
    }
*/
    /*@Test
    void createWalls() throws IOException {
        BattlefieldBuilder battlefieldBuilder = Mockito.mock(BattlefieldBuilder.class);

        LanternaGUI gui = Mockito.mock(LanternaGUI.class);

        BattlefieldLoader battlefieldLoader = new BattlefieldLoader(1);
        battlefieldLoader.createBattlefield(25,25);
        Mockito.verify(battlefieldBuilder,Mockito.times(1)).createWalls(25,25);
    }*/
}