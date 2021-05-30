package com.g57.controller;

import com.g57.Game;
import com.g57.model.Position;
import com.g57.model.battlefield.Battlefield;
import com.g57.model.element.bullet.Bullet;
import com.g57.model.element.enemy.Enemy;

import java.io.IOException;

public class EnemyController extends GameController{
    private final BattlefieldController battlefieldController;
    private long lastMovement;
    private long beginningGame;

    public EnemyController(Battlefield battlefield, BattlefieldController battlefieldController) {
        super(battlefield);

        this.battlefieldController = battlefieldController;
        this.lastMovement = 0;
        this.beginningGame = 0;
    }

    @Override
    public void step(Game game, long time) throws IOException {
        if(this.beginningGame == 0) this.beginningGame = time;

        if (time - lastMovement > 250) {
            for (Enemy enemy : getModel().getEnemies()) {
                Position position;
                do {
                    position = enemy.getPosition().getRandomNeighbour(enemy.getSpeed());
                } while(!verifyPosition(game, position));
                moveEnemy(enemy, position);
                this.lastMovement = time;

                if((time - this.beginningGame > 1000) && (time - enemy.getLastShot() > 1250)) {
                    if (shouldShoot(enemy.getPosition(), enemy.getGun().getRange())) {
                        shoot(enemy, getModel().getPlayer().getPosition());
                        enemy.setLastShot(time);
                    }
                }
            }
        }
    }

    public void shoot(Enemy enemy, Position position){
        Bullet bullet = battlefieldController.getBullet();
        bulletConfig(enemy, bullet);
        double deltaX = position.getX()-bullet.getX();
        double deltaY = position.getY()-bullet.getY();

        bullet.setDegree(Math.atan2(deltaY, deltaX));

        getModel().insertBullet(bullet);
    }

    public boolean shouldShoot(Position enemyPos, double range) {
        Position playerPos = getModel().getPlayer().getPosition();
        double distance = Math.sqrt(Math.pow(playerPos.getX() - enemyPos.getX(), 2) + Math.pow(playerPos.getY() - enemyPos.getY(), 2));

        return distance <= range;
    }

    private void moveEnemy(Enemy enemy, Position position) {
        if (getModel().isEmpty(position)) {
            enemy.setPosition(position);
            if (getModel().getPlayer().getPosition().equals(position))
                getModel().getPlayer().changeEnergy(-1);
        }
    }

    public boolean verifyPosition(Game game, Position position) {
        Battlefield battlefield = getModel();
        return battlefield.isEmpty(position) && insideBattlefield(game, position) && !battlefield.isBomb(position)
                && battlefield.isObstacle(position) == null && !battlefield.isShop(position);
    }

    public void bulletConfig(Enemy enemy, Bullet bullet){
        bullet.setSpeed(enemy.getGun().getSpeed());
        bullet.setX(enemy.getPosition().getX());
        bullet.setY(enemy.getPosition().getY());
        bullet.setDamage(enemy.getGun().getDamage());
        bullet.setRange(enemy.getGun().getRange());
        bullet.setFiredByPlayer(false);
    }

    private boolean insideBattlefield(Game game, Position position) {
        int x = position.getX();
        int y = position.getY();
        return (x >= 1) && (x <= game.getWidth() - 1) && (y >= 1) && (y <= game.getHeight() - 1);
    }

}
