package com.g57.state;

import com.g57.Game;
import com.g57.controller.StoreController;
import com.g57.gui.GUI;
import com.g57.model.Position;
import com.g57.model.Store;
import com.g57.model.element.Player;
import com.g57.model.element.button.Button;
import com.g57.model.item.command.MenuButtonCommand;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class StoreState extends GameState{
    private StoreController shopController;

    public StoreState(Game game, GUI gui, GameState previousState, Player player, Store store) {
        super(game, Collections.singletonList(
                new Button(new Position( 20,4), "d", new MenuButtonCommand(previousState),
                        Arrays.asList("#FFFF00","#FFFFFF"))
        ));

        getButtons().get(0).changeColor();
        this.shopController = new StoreController(this, gui, player, store);
    }

    @Override
    public void start() {
        game.getMouseObserver().setListener(shopController);
        game.getKeyBoardObserver().setListener(shopController);
    }

    @Override
    public void step(Game game, long time) throws IOException {
        shopController.step();
    }

    public StoreController getStoreController() {
        return shopController;
    }

    public void setStoreController(StoreController shopController) {
        this.shopController = shopController;
    }
}
