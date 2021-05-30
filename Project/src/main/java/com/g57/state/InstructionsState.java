package com.g57.state;

import com.g57.Game;
import com.g57.controller.InstructionsController;
import com.g57.gui.GUI;
import com.g57.model.Position;
import com.g57.model.element.button.Button;
import com.g57.model.item.command.MenuButtonCommand;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class InstructionsState extends GameState{

    private InstructionsController instructionsController;

    public InstructionsState(Game game, GUI gui) throws IOException {
        super(game, Collections.singletonList(
                new Button(new Position(20, 22),"PLAY", new MenuButtonCommand(new PlayingState(game, gui, 1)),
                        Arrays.asList("#FFFFFF", "#FFFF00"))
        ));

        instructionsController = new InstructionsController(this,gui);
    }

    @Override
    public void start() {
        game.getMouseObserver().setListener(instructionsController);
        game.getKeyBoardObserver().setListener(instructionsController);
    }

    @Override
    public void step(Game game, long time) throws IOException {
      instructionsController.step();
    }

    public InstructionsController getInstructionsController() {
        return instructionsController;
    }

    public void setInstructionsController(InstructionsController instructionsController) {
        this.instructionsController = instructionsController;
    }
}
