package com.g57.viewer.state;

import com.g57.gui.GUI;
import com.g57.model.element.button.Button;
import com.g57.viewer.element.ButtonViewer;

import java.io.IOException;
import java.util.List;

public class PauseViewer extends StateViewer{

    public PauseViewer(GUI gui, List<Button> buttons) {
        super(gui, buttons);
    }

    public void draw() throws IOException {
        gui.clear();

        drawBackground("#000000");
        drawButtons(buttons, new ButtonViewer());

        gui.refresh();
    }


}
