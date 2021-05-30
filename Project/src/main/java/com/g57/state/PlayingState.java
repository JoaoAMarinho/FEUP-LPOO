package com.g57.state;

import com.g57.Game;
import com.g57.controller.PlayingController;
import com.g57.gui.GUI;
import com.g57.model.Position;
import com.g57.model.Store;
import com.g57.model.battlefield.Battlefield;
import com.g57.model.battlefield.BattlefieldLoader;
import com.g57.model.element.Player;
import com.g57.model.element.button.Button;
import com.g57.model.item.EnergyPotion;
import com.g57.model.item.SpeedPotion;
import com.g57.model.item.command.ConsumePotionCommand;
import com.g57.model.item.command.EnergyPotionCommand;
import com.g57.model.item.command.SpeedPotionCommand;

import java.io.IOException;
import java.util.Arrays;

public class PlayingState extends GameState {
    private int level;
    private final int maxLevel;
    private PlayingController playingController;
    private Battlefield battlefield;

    public PlayingState(Game game, GUI gui, int level) throws IOException {
        super(game, Arrays.asList(
                new Button(new Position(gui.getWidth()-1, gui.getHeight()), new EnergyPotionCommand(new EnergyPotion("#FFFFFF"))
                        ,Arrays.asList("#FFFFFF", "#FF0000")),
                new Button(new Position(gui.getWidth()-3, gui.getHeight()), new SpeedPotionCommand(new SpeedPotion("#FFFFFF"))
                    ,Arrays.asList("#FFFFFF", "#FF0000")) )
        );
        this.level = level;
        this.maxLevel = 7;
        this.battlefield = new BattlefieldLoader(level).createBattlefield(game.getWidth(), game.getHeight());
        this.playingController = new PlayingController(this, gui,battlefield, new Store(gui));
        setPlayerController();
    }

    @Override
    public void start() {
        game.getMouseObserver().setListener(playingController);
        game.getKeyBoardObserver().setListener(playingController);
    }

    @Override
    public void step(Game game, long time) throws IOException {
        playingController.step(game, time);
    }

    public boolean upLevel() throws IOException {
        if(++level > maxLevel) return false;
        Player player=battlefield.getPlayer();
        this.battlefield = new BattlefieldLoader(level).createBattlefield(game.getWidth(), game.getHeight());
        this.battlefield.setPlayer(player);
        this.playingController.setupModel(battlefield);

        resetValues();

        return true;
    }

    private void resetValues() {
        playingController.getBattlefieldController().resetPlayer();
        resetButtons();
    }

    private void resetButtons() {
        for (Button button : getButtons()){
            button.getCommand().undo();
            button.deactivate();
        }
    }

    private void setPlayerController(){
        for (Button button: getButtons()){
            ConsumePotionCommand consumeCommand = (ConsumePotionCommand) button.getCommand();
            consumeCommand.setPlayerController(playingController.getBattlefieldController().getPlayerController());
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public PlayingController getPlayingController() {
        return playingController;
    }

    public void setPlayingController(PlayingController playingController) {
        this.playingController = playingController;
    }

    public Battlefield getBattlefield() {
        return battlefield;
    }

    public void setBattlefield(Battlefield battlefield) {
        this.battlefield = battlefield;
    }
}
