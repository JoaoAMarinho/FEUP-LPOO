package com.g57.controller;

import com.g57.Game;
import com.g57.gui.GUI;
import com.g57.model.Position;
import com.g57.model.battlefield.Battlefield;
import com.g57.model.element.Portal;
import com.g57.model.element.bullet.Bullet;
import com.g57.model.element.bullet.BulletPool;
import com.g57.model.element.enemy.Enemy;
import com.g57.model.element.obstacle.Obstacle;
import com.g57.state.GameState;
import com.g57.viewer.BattleFieldViewer;

import java.io.IOException;
import java.util.List;

public class BattlefieldController extends GameController {
    private final GameState gameState;
    private final BattleFieldViewer battleFieldViewer;
    private final EnemyController enemyController;
    private PlayerController playerController;
    private final BombController bombController;
    private BulletPool bulletPool;

    public BattlefieldController(GameState gameState, GUI gui, Battlefield battlefield) {
        super(battlefield);
        this.gameState = gameState;
        this.battleFieldViewer = new BattleFieldViewer(gui, battlefield);
        this.enemyController = new EnemyController(battlefield, this);
        this.playerController = new PlayerController(battlefield.getPlayer());
        this.bulletPool= new BulletPool();
        this.bombController = new BombController(battlefield);
    }

    @Override
    public void step(Game game, long time) throws IOException {
        updateBullets();
        enemyController.step(game, time);
        bombController.step(game, time);
        battleFieldViewer.draw();
    }

    private void updateBullets() {
        List<Bullet> bullets = getModel().getBullets();
        for (int i = 0; i < bullets.size(); i++){
            Bullet bullet=bullets.get(i);
            if(bullet.isFiredByPlayer()) {
                for (int j = 0; j < bullet.getSpeed(); j++) {
                    if (!bullet.updatePos() || !getModel().isEmpty(bullet.getPosition())) {
                        Enemy enemy = getModel().isEnemy(bullet.getPosition());
                        Obstacle obstacle = getModel().isObstacle(bullet.getPosition());

                        if (enemy != null) {
                            enemy.decreaseEnergy(bullet.getDamage());
                            if (enemy.getEnergy() <= 0) {
                                getModel().getPlayer().updateBudget(enemy.getBounty());
                                getModel().getEnemies().remove(enemy);
                            }
                        } else if(obstacle != null ){
                            obstacle.decreaseEnergy(bullet.getDamage());
                            if(obstacle.getEnergy() <= 0) getModel().getObstacles().remove(obstacle);
                        }
                        removeBullets(bullet);
                        i--;
                        break;
                    }
                }
            } else {
                for (int j = 0; j < bullet.getSpeed(); j++) {
                    Obstacle obstacle = getModel().isObstacle(bullet.getPosition());
                    if (!bullet.updatePos() || getModel().isPlayer(bullet.getPosition()) ||
                            getModel().isWall(bullet.getPosition()) || getModel().isPortal(bullet.getPosition()) != null
                            || obstacle != null) {
                        if(getModel().isPlayer(bullet.getPosition())) {
                            getModel().getPlayer().changeEnergy(-bullet.getDamage());
                        }
                        else if(obstacle != null) {
                            obstacle.decreaseEnergy(bullet.getDamage());
                            if(obstacle.getEnergy() <= 0) getModel().getObstacles().remove(obstacle);
                        }
                        removeBullets(bullet);
                        i--;
                        break;
                    }
                }
            }
        }
    }

    public void removeBullets(Bullet bullet) {
        bulletPool.insertBullet(bullet);
        getModel().removeBullet(bullet);
    }

    public Bullet getBullet() {
        return bulletPool.removeBullet();
    }

    public PlayerController getPlayerController() {
        return playerController;
    }


    public void doAction(GUI.ACTION action) {
        Position nextPos = playerController.doAction(action);
        if(nextPos==null) return;
        if (outOfBounds(nextPos)) return;
        if(nextPos.getX()<0 || nextPos.getY()<0 ) return;

        checkCollision(nextPos);
    }

    private boolean outOfBounds(Position pos) {
        Game game = gameState.getGame();
        return pos.getX()<0 || pos.getY()<0 || pos.getX()>=game.getWidth() || pos.getY()>=game.getHeight();
    }

    public void shoot(Position position){
        Bullet bullet = bulletPool.removeBullet();
        playerController.bulletConfig(bullet);
        double deltaX = position.getX()-bullet.getX()* gameState.getGame().getWidth();
        double deltaY = position.getY()-bullet.getY()* gameState.getGame().getHeight();

        bullet.setDegree(Math.atan2(deltaY, deltaX));

        getModel().insertBullet(bullet);
    }

    private void checkCollision(Position position){

        Position playerPosition = checkPortalCollision(position);
        if(playerPosition!=null){
            playerController.movePlayer(playerPosition);
            return;
        }

        if(checkWallCollision(position)) return;
        if(checkObstacleCollision(position)) return;
        if(checkShopCollision(position)) return;

        playerController.movePlayer(position);
    }

    private boolean checkShopCollision(Position position) {
        return getModel().isShop(position);
    }

    private Position checkPortalCollision(Position position){
        Portal portal = getModel().isPortal(position);
        if(portal!=null)
            return portal.getLeavePos(gameState.getGame().getWidth(), gameState.getGame().getHeight());
        return null;
    }

    private boolean checkWallCollision(Position position){
        return getModel().isWall(position);
    }

    private boolean checkObstacleCollision(Position position) {
        return getModel().isObstacle(position) != null;
    }

    public boolean nearShop() {
        Position shopPos = getModel().getShop().getPosition();
        Position playerPos = getModel().getPlayer().getPosition();
        return (Math.sqrt( Math.pow(shopPos.getX()-playerPos.getX(),2)+Math.pow(shopPos.getY()-playerPos.getY(),2)) <= 1.5);
    }

    public void resetPlayer() {
        playerController.resetPlayer();
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public void setBulletPool(BulletPool bulletPool) {
        this.bulletPool = bulletPool;
    }
}
