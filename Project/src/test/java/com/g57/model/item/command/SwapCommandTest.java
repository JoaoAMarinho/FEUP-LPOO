package com.g57.model.item.command;

import com.g57.model.element.button.Button;
import com.g57.model.item.Gun;
import com.g57.model.item.Item;
import com.g57.model.item.command.buyCommand.GunCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwapCommandTest {
    private SwapCommand swpCmd1;
    private SwapCommand swpCmd2;
    private Item item1;
    private Item item2;
    private Button button1;
    private Button button2;
    private GunCommand gunCommand;

    @BeforeEach
    void setUp() {

        button1 = Mockito.mock(Button.class);
        Mockito.when(button1.isActive()).thenReturn(true);
        button2 = Mockito.mock(Button.class);
        Mockito.when(button2.isActive()).thenReturn(false);

        gunCommand = Mockito.mock(GunCommand.class);

        item1 = Mockito.mock(Gun.class);
        Mockito.when(gunCommand.getItem()).thenReturn(item1);

        item2 = Mockito.mock(Gun.class);

        Mockito.when(button1.getCommand()).thenReturn(gunCommand);
        Mockito.when(button2.getCommand()).thenReturn(gunCommand);

        swpCmd1 = new SwapCommand(item2, button1);
        swpCmd2 = new SwapCommand(item2, button2);
    }

    @Test
    void execute() {
        swpCmd1.execute();
        Mockito.verify(button1,Mockito.times(1)).isActive();
        Mockito.verify(gunCommand, Mockito.times(0)).undo();
        Mockito.verify(gunCommand, Mockito.times(1)).resetButtons();
        assertEquals(swpCmd1.getItem(), item1);

        swpCmd2.execute();
        Mockito.verify(button2,Mockito.times(1)).isActive();
        Mockito.verify(gunCommand, Mockito.times(1)).undo();
        assertEquals(swpCmd2.getItem(), item1);
    }

    @Test
    void item() {
        swpCmd1.setItem(item1);
        assertEquals(item1, swpCmd1.getItem());

        assertEquals(swpCmd2.getItem(), item2);
    }
}
