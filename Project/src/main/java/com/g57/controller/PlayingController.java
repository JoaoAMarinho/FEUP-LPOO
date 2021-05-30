package com.g57.controller;

import com.g57.Game;
import com.g57.gui.GUI;
import com.g57.model.Position;
import com.g57.model.Store;
import com.g57.model.battlefield.Battlefield;
import com.g57.model.element.button.Button;
import com.g57.state.*;
import com.g57.state.listener.KeyBoardListener;
import com.g57.state.listener.MouseListener;
import com.g57.viewer.state.EndGameViewer;
import com.g57.viewer.state.LoadingLevelViewer;
import com.g57.viewer.state.PlayingViewer;
import com.g57.viewer.state.StateViewer;

import java.io.IOException;
import java.util.ArrayList;

public class PlayingController extends GameController implements MouseListener, KeyBoardListener {
    private final GameState gameState;
    private BattlefieldController battlefieldController;
    private final GUI gui;
    private final StateViewer playingViewer;
    private final Store store;
    private long endTime;

    public PlayingController(GameState gameState, GUI gui, Battlefield battlefield, Store store) {
        super(battlefield);
        this.gameState = gameState;
        this.gui = gui;
        this.battlefieldController = new BattlefieldController(gameState,gui,battlefield);
        this.playingViewer = new PlayingViewer(gui, new ArrayList<>(), battlefield.getPlayer());
        this.store = store;
        this.endTime = 0;
    }

    public void step(Game game, long time) throws IOException {
        if(this.endTime == 0) {
            battlefieldController.step(game, time);
            playingViewer.draw();
            if (getModel().getEnemies().isEmpty()) {
                this.endTime = ((PlayingState) gameState).upLevel() ? 0 : time;
                if(this.endTime == 0) {
                    LoadingLevelViewer loadingLevelViewer = new LoadingLevelViewer(gui, null, ((PlayingState) gameState).getLevel());
                    loadingLevelViewer.draw();
                } else {
                    EndGameViewer endGameViewer = new EndGameViewer(gui, null, true);
                    endGameViewer.draw();
                }

            } else if (getModel().getPlayer().getEnergy() <= 0) {
                this.endTime = time;
                EndGameViewer endGameViewer = new EndGameViewer(gui, null, false);
                endGameViewer.draw();
            }
        }
        else{
            endGame(time);
        }

        checkButtons();

    }

    private void changeState(GameState gameState){
        this.gameState.changeState(gameState);
    }

    public void endGame(long time) throws IOException {

        if(time - this.endTime > 1500) {
            changeState(new MenuState(this.gameState.getGame(), gui));
        }
    }

    @Override
    public void keyPressed(GUI.ACTION action) {
        if (action == GUI.ACTION.QUIT){
            try {
                changeState(new PauseState(gameState.getGame(), this.gui, this.gameState));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        if (action == GUI.ACTION.SHOP && battlefieldController.nearShop()){
            changeState(new StoreState(this.gameState.getGame(), this.gui, this.gameState, getModel().getPlayer(), store));
            return;
        }

        battlefieldController.doAction(action);
    }

    @Override
    public void click(Position position) {
        Position clickedPos = getRealPosition(position,gameState.getGame().getWidth(), gameState.getGame().getHeight());

        for (Button button : gameState.getButtons()){
            if(button.isActive()) continue;

            Position buttonPos = button.getPosition();
            if (buttonPosition(clickedPos, buttonPos, button.getWidth(), button.getHeight())){
                button.getCommand().execute();
                button.activate();
                return;
            }
        }
        battlefieldController.shoot(position);
    }

    @Override
    public void move(Position position) {}

    public void checkButtons() {
        for(Button button: gameState.getButtons()) {
            if(!button.isActive()) continue;

            if (!button.getCommand().execute()) button.deactivate();
        }
    }

    public BattlefieldController getBattlefieldController() {
        return battlefieldController;
    }

    public void setupModel(Battlefield battlefield) {
        this.battlefieldController = new BattlefieldController(gameState,gui,battlefield);
        this.setModel(battlefield);
    }
}
