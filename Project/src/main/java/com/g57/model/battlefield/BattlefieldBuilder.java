package com.g57.model.battlefield;

import com.g57.model.Position;
import com.g57.model.element.*;
import com.g57.model.element.enemy.Enemy;
import com.g57.model.element.obstacle.Obstacle;

import java.util.ArrayList;
import java.util.List;

public abstract class BattlefieldBuilder {

    public Battlefield createBattlefield(int width, int height){
        Battlefield battlefield = new Battlefield();
        battlefield.setPlayer(createPlayer());
        battlefield.setWalls(createWalls(width, height));
        battlefield.setPortals(createPortals());
        battlefield.setEnemies(createEnemies(width, height));
        battlefield.removeWallPortalCollision();
        battlefield.setBombs(createBombs());
        battlefield.setObstacleS(createObstaclesS());
        battlefield.setShop(createShop());
        return battlefield;
    }

    protected abstract Player createPlayer();

    protected abstract Shop createShop();

    protected abstract List<Enemy> createEnemies(int width, int height);

    protected abstract List<Portal> createPortals();

    protected abstract List<Bomb> createBombs();

    protected abstract List<Obstacle> createObstaclesS();

    protected List<Wall> createWalls(int width, int height) {
        List<Wall> walls = new ArrayList<>();

        for(int i = 0; i < width; i++) {
            walls.add(new Wall(new Position(i, 0), "#1223FF"));
            walls.add(new Wall(new Position(i, height - 1), "#1223FF"));
        }

        for(int i = 1; i <  height - 1; i++) {
            walls.add(new Wall(new Position(0, i), "#1223FF"));
            walls.add(new Wall(new Position(width - 1, i), "#1223FF"));
        }

        return walls;
    }
}
