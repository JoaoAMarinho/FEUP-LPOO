package com.g57.controller;

import com.g57.gui.GUI;
import com.g57.model.Position;
import com.g57.model.element.Player;
import com.g57.model.element.bullet.Bullet;
import com.g57.model.item.Gun;
import com.g57.model.item.Item;

public class PlayerController {
    private final Player player;

    public PlayerController(Player player) {
        this.player = player;
    }

    public Position movePlayerLeft() {
        return this.player.getPosition().getLeft(this.player.getSpeed());
    }
    public Position movePlayerRight() {
        return this.player.getPosition().getRight(this.player.getSpeed());
    }
    public Position movePlayerUp() {
        return this.player.getPosition().getUp(this.player.getSpeed());
    }
    public Position movePlayerDown() {
        return this.player.getPosition().getDown(this.player.getSpeed());
    }
    public void movePlayer(Position position) {
        player.setPosition(position);
    }

    public Position doAction(GUI.ACTION action) {
        if (action == GUI.ACTION.UP) return movePlayerUp();
        if (action == GUI.ACTION.RIGHT) return movePlayerRight();
        if (action == GUI.ACTION.DOWN) return movePlayerDown();
        if (action == GUI.ACTION.LEFT) return movePlayerLeft();
        return null;
    }

    public void bulletConfig(Bullet bullet){
        bullet.setSpeed(player.getGun().getSpeed());
        bullet.setX(player.getPosition().getX());
        bullet.setY(player.getPosition().getY());
        bullet.setDamage(player.getGun().getDamage());
        bullet.setRange(player.getGun().getRange());
        bullet.setFiredByPlayer(true);
    }

    public boolean canBuy(int value){
        return player.getBudget() >= value;
    }
    public boolean canUpGunLevel(){
        return player.getGun().increaseLevel();
    }
    public void changeGun(Gun gun) {
        player.setGun(gun);
    }

    public void reduceGunLevel(){
        player.getGun().decreaseLevel();
    }
    public void increaseGunSpeed(){ player.getGun().increaseSpeed(); }
    public void decreaseGunSpeed(){player.getGun().decreaseSpeed();}
    public void increaseGunDamage(){
        player.getGun().increaseDamage();
    }
    public void decreaseGunDamage(){player.getGun().decreaseDamage();}

    public void addItemToPlayer(Item item){
        player.addPotion(item);
    }

    public void updateBudget(int value){
        player.updateBudget(value);
    }

    public void removePotion(Item item) {
        player.removePotion(item);
    }

    public void changeEnergy(int value) { player.changeEnergy(value);}

    public void changeSpeed(int value) { player.changeSpeed(value); }

    public Player getPlayer() {
        return player;
    }

    public boolean hasItem(Item item) {
        return player.getPotionList().get(item) > 0;
    }

    public void potionColorEffect() {
        player.potionColorEffect();
    }

    public void resetPlayer() {
        player.setPosition(new Position(6, 6));
        player.multiplyBudget(0.5);
        player.setEnergy(player.getMaxEnergy());
    }

    public void setDefaultColor() {
        player.setDefaultColor();
    }
}
