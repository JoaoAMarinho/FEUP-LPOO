package com.g57.model.element;

import com.g57.model.Position;
import com.g57.model.item.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player extends Element {
    private final List<String> colors;
    private final String defaultColor;
    private Gun gun;
    private int budget;
    private final int maxEnergy;
    private int energy;
    private int speed;
    private Map<Item, Integer> potionList;

    public Player(Position position, List<String> colors, Gun gun, int energy) {
        super(position, "#FFDB58");
        this.colors = colors;
        this.defaultColor = "#FFDB58";
        this.gun = gun;
        this.maxEnergy = energy;
        this.energy = energy;
        this.speed = 1;
        this.budget = 4;
        this.potionList = new HashMap<>();
        this.potionList.put(new EnergyPotion("#FFFFFF"),0);
        this.potionList.put(new SpeedPotion( "#FF0000"),0);
    }

    public int getBudget() {
        return budget;
    }
    public void updateBudget(int value) {
        this.budget += value;
    }
    public void multiplyBudget(double value) {
        this.budget *= value;
    }


    public Gun getGun() {
        return gun;
    }
    public void setGun(Gun gun) {
        this.gun = gun;
    }

    public int getEnergy() {
        return energy;
    }
    public void setEnergy(int energy) {
        this.energy = Math.min(energy, this.maxEnergy);
    }
    public int getMaxEnergy() {
        return maxEnergy;
    }
    public void changeEnergy(int value) { setEnergy(this.energy + value); }

    public void setDefaultColor(){
        this.color = defaultColor;
    }

    public int getSpeed() {
        return speed;
    }
    public void changeSpeed(int value) {
        this.speed += value;
    }

    public Map<Item, Integer> getPotionList() {
        return potionList;
    }
    public void addPotion(Item item){
        potionList.put(item, potionList.getOrDefault(item, 0)+1);
    }
    public void removePotion(Item item){
        potionList.put(item, potionList.get(item)-1);
    }
    public void potionColorEffect() {
        this.setColor(colors.get((colors.indexOf(color)+1)%colors.size()));
    }
}
