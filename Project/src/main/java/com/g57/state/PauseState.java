package com.g57.state;

import com.g57.Game;
import com.g57.controller.PauseController;
import com.g57.gui.GUI;
import com.g57.model.Position;
import com.g57.model.element.button.Button;
import com.g57.model.item.command.MenuButtonCommand;

import java.io.IOException;
import java.util.Arrays;

public class PauseState extends GameState {
    private PauseController pauseController;

    public PauseState(Game game, GUI gui, GameState previousState) throws IOException {
        super(game, Arrays.asList(
                new Button(new Position(Button.getButtonX(game.getWidth(), "RESUME"),7),"RESUME",
                        new MenuButtonCommand(previousState), Arrays.asList("#FFFFFF", "#FFFF00")),
                new Button(new Position(Button.getButtonX(game.getWidth(), "EXIT"),14), "EXIT",
                        new MenuButtonCommand(new MenuState(game,gui)), Arrays.asList("#FFFFFF", "#FFFF00"))
        ));

        pauseController = new PauseController(this, gui);
    }

    @Override
    public void start() {
        game.getMouseObserver().setListener(pauseController);
        game.getKeyBoardObserver().setListener(pauseController);
    }

    @Override
    public void step(Game game, long time) throws IOException {
        this.pauseController.step();
    }

    public PauseController getPauseController() {
        return pauseController;
    }

    public void setPauseController(PauseController pauseController) {
        this.pauseController = pauseController;
    }
}
