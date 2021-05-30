package com.g57.controller;

import com.g57.Game;
import com.g57.model.battlefield.Battlefield;
import com.g57.model.element.Bomb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BombController extends GameController {

    public BombController(Battlefield battlefield) {
        super(battlefield);
    }

    @Override
    public void step(Game game, long time) throws IOException {
        List<Bomb> removeBombs = new ArrayList<>();
        for(Bomb bomb : getModel().getBombs()) {
            if(bomb.isActive()) {
                if (time - bomb.getActiveTime() > 200) {
                    removeBombs.add(bomb);
                }
            } else {
                if(bomb.getPosition().equals(getModel().getPlayer().getPosition())) {
                    bomb.setActive(true);
                    getModel().getPlayer().changeEnergy(-bomb.getDamage());
                    bomb.setActiveTime(time);
                }
            }
        }
        if(!removeBombs.isEmpty())
            for (Bomb bomb:
                 removeBombs) {
                getModel().getBombs().remove(bomb);
            }
    }
}
