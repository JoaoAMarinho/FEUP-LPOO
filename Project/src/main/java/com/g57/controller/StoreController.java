package com.g57.controller;

import com.g57.gui.GUI;
import com.g57.model.Position;
import com.g57.model.Store;
import com.g57.model.element.Player;
import com.g57.model.element.button.Button;
import com.g57.model.item.Gun;
import com.g57.model.item.command.DisplayInfoCommand;
import com.g57.model.item.command.SwapCommand;
import com.g57.model.item.command.buyCommand.BuyCommand;
import com.g57.state.GameState;
import com.g57.state.listener.KeyBoardListener;
import com.g57.state.listener.MouseListener;
import com.g57.viewer.state.StateViewer;
import com.g57.viewer.state.StoreViewer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StoreController implements MouseListener, KeyBoardListener {
    private final GameState gameState;
    private final StateViewer storeViewer;
    private final Store store;
    private final PlayerController playerController;

    public StoreController(GameState gameState, GUI gui, Player player, Store store) {
        this.gameState = gameState;
        this.playerController = new PlayerController(player);
        this.store = store;
        setupStoreGuns(player);
        setPlayerController();

        List<Button> buttons = new ArrayList<>();
        buttons.addAll(gameState.getButtons());
        buttons.addAll(store.getButtons());
        buttons.add(store.getDisplayGunInfoButton());
        this.storeViewer = new StoreViewer(gui, buttons, player);

        activateButtons();
    }

    private void setPlayerController() {
        for (Button button: store.getButtons()){
            if (!(button.getCommand() instanceof BuyCommand)) continue;
            BuyCommand buyCommand = (BuyCommand) button.getCommand();
            buyCommand.setPlayerController(playerController);
        }
        ((DisplayInfoCommand)store.getDisplayGunInfoButton().getCommand()).setPlayerController(playerController);
    }

    private void setupStoreGuns(Player player) {
        List<Integer> rangeList = Arrays.asList(8, 10, 12);

        boolean changedGun1 = false;
        int rIndex = 0;
        while (rIndex<rangeList.size()){

            if (player.getGun().getRange() == rangeList.get(rIndex)){
                rIndex++;
                continue;
            }
            Random random = new Random();

            if (!changedGun1){
                ((BuyCommand)(store.getButtons().get(5).getCommand())).setItem(new Gun((random.nextInt(5) + 13) - rangeList.get(rIndex),
                        rangeList.get(rIndex), random.nextInt(2) + 1, "#000000",3,1,random.nextInt(6) + 5));
                changedGun1 = true;
            }
            else {
                ((SwapCommand)(store.getButtons().get(4).getCommand())).setItem(new Gun((random.nextInt(5) + 13) - rangeList.get(rIndex),
                        rangeList.get(rIndex), random.nextInt(2)+ 1 , "#000000",3,1,random.nextInt(6)+ 5));
            }


            rIndex++;
        }
    }

    public void step() throws IOException {
        storeViewer.draw();
        checkGunDisplayInfo();
    }

    private void checkGunDisplayInfo() {
        if (store.getDisplayGunInfoButton().isActive()){
            store.getDisplayGunInfoButton().getCommand().execute();
        }
    }


    @Override
    public void keyPressed(GUI.ACTION action) {
    }

    @Override
    public void click(Position position) {
        Position clickedPos = getRealPosition(position,gameState.getGame().getWidth(), gameState.getGame().getHeight());

        List<Button> buttons = new ArrayList<>();
        buttons.addAll(gameState.getButtons());
        buttons.addAll(store.getButtons());

        for (Button button : buttons){
            Position buttonPos = button.getPosition();
            if (buttonPosition(clickedPos, buttonPos, button.getWidth(), button.getHeight())){
                if (!button.isActive()){
                    button.getCommand().undo();
                    if (button.getCommand() instanceof BuyCommand) button.activate();
                    return;
                }
                if(button.getCommand().execute()){
                    if (button.getCommand() instanceof BuyCommand)
                        button.deactivate();
                }
                return;
            }
        }

    }

    private void activateButtons() {
        for (Button button : store.getButtons()){
            button.activate();
        }
    }


    @Override
    public void move(Position position) {
        Position currentPosition = getRealPosition(position,gameState.getGame().getWidth(), gameState.getGame().getHeight());
        Button button = store.getDisplayGunInfoButton();
        if(buttonPosition(currentPosition, button.getPosition(), button.getWidth(), button.getHeight())){
            button.getCommand().execute();
            button.highlight();
            button.activate();
            return;
        }
        if(button.isHighlighted()) {
            button.toneDown();
            button.deactivate();
            button.getCommand().undo();
        }
    }

}
