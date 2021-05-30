package com.g57.state;

import com.g57.Game;
import com.g57.controller.MenuController;
import com.g57.gui.GUI;
import com.g57.model.Position;
import com.g57.model.element.button.Button;
import com.g57.model.item.command.MenuButtonCommand;

import java.io.IOException;
import java.util.Arrays;

public class MenuState extends GameState {
    private MenuController menuController;

    public MenuState(Game game, GUI gui) throws IOException {
        super(game, Arrays.asList(
                new Button(new Position(Button.getButtonX(game.getWidth(), "PLAY"),7),
                            "PLAY",new MenuButtonCommand(new PlayingState(game,gui,1)),Arrays.asList("#FFFFFF", "#FFFF00")),
                new Button(new Position(Button.getButtonX(game.getWidth(), "INSTRUCTIONS"), 14),
                            "INSTRUCTIONS",new MenuButtonCommand(new InstructionsState(game, gui)),Arrays.asList("#FFFFFF", "#FFFF00")),
                new Button(new Position(Button.getButtonX(game.getWidth(), "EXIT"),21),
                            "EXIT", new MenuButtonCommand(null), Arrays.asList("#FFFFFF", "#FFFF00"))
        ));

        menuController = new MenuController(this, gui);
    }

    @Override
    public void start() {
        game.getMouseObserver().setListener(menuController);
        game.getKeyBoardObserver().setListener(menuController);
    }

    @Override
    public void step(Game game, long time) throws IOException {
        this.menuController.step();
    }

    public MenuController getMenuController() {
        return menuController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }
}
