package com.g57.model.battlefield;

import com.g57.model.Position;
import com.g57.model.element.*;
import com.g57.model.element.bullet.Bullet;
import com.g57.model.element.enemy.Enemy;
import com.g57.model.element.obstacle.Obstacle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Battlefield {
    private List<Enemy> enemies;
    private List<Portal> portals;
    private List<Bomb> bombs;
    private List<Obstacle> obstacles;
    private List<Wall> walls;
    private Player player;
    private Shop shop;
    private List<Bullet> bullets;

    public Battlefield() {
        bullets = new ArrayList<>();
    }

    public void setPortals(List<Portal> portals) {
        this.portals = portals;
    }

    public void setBombs(List<Bomb> bombs) {
        this.bombs = bombs;
    }

    public void setObstacleS(List<Obstacle> obstacleS) {
        this.obstacles = obstacleS;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Player getPlayer() {
        return player;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Portal> getPortals() {
        return portals;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public Shop getShop() {
            return shop;
    }

    public void insertBullet(Bullet bullet){
        bullets.add(bullet);
    }
    public void removeBullet(Bullet bullet){
        bullets.remove(bullet);
    }

    public boolean isEmpty(Position position) {
        return !isWall(position) && isPortal(position)==null && isEnemy(position) == null  && isObstacle(position) == null;
    }

    public boolean isWall(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return true;
        return false;
    }

    public Portal isPortal(Position position){
        for (Portal portal : portals)
            if (portal.getPosition().equals(position))
                return portal.getDest();
        return null;
    }

    public Enemy isEnemy(Position position) {
        for(Enemy enemy : enemies) {
            if(enemy.getPosition().equals(position))
                return enemy;
        }

        return null;
    }

    public boolean isBomb(Position position) {
        for(Bomb bomb: bombs) {
            if(bomb.getPosition().equals(position))
                return true;
        }
        return false;
    }

    public void removeWallPortalCollision() {
        for (Portal portal:portals){
            if(isWall(portal.getPosition()))
                walls.remove(new Wall(portal.getPosition(), ""));
        }
    }

    public boolean isPlayer(Position position) {
        return position.equals(this.player.getPosition());
    }

    public Obstacle isObstacle(Position position) {
        for(Obstacle obstacle: obstacles)
            if(obstacle.getPosition().equals(position))
                return obstacle;
        return null;
    }

    public boolean isShop(Position position) {
        return shop.getPosition().equals(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battlefield that = (Battlefield) o;
        return Objects.equals(enemies, that.enemies) && Objects.equals(portals, that.portals) && Objects.equals(bombs, that.bombs) && Objects.equals(obstacles, that.obstacles) && Objects.equals(walls, that.walls) && Objects.equals(player, that.player) && Objects.equals(shop, that.shop) && Objects.equals(bullets, that.bullets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enemies, portals, bombs, obstacles, walls, player, shop, bullets);
    }
}
