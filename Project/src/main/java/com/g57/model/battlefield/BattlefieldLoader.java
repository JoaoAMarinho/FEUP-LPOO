package com.g57.model.battlefield;

import com.g57.model.Position;
import com.g57.model.element.*;
import com.g57.model.element.enemy.BigEnemy;
import com.g57.model.element.enemy.Enemy;
import com.g57.model.element.enemy.MediumEnemy;
import com.g57.model.element.enemy.SmallEnemy;
import com.g57.model.element.obstacle.Obstacle;
import com.g57.model.item.Gun;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BattlefieldLoader extends BattlefieldBuilder {
    private final List<String> lines;

    public BattlefieldLoader(int level) throws IOException {
        URL resource = BattlefieldBuilder.class.getResource("/levels/level" + level);
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));

        this.lines = readLines(br);
    }

    private List<String> readLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; )
            lines.add(line);
        return lines;
    }

    @Override
    protected Player createPlayer() {
        return new Player(new Position(6, 6), Arrays.asList("#FF00FF","#FF69B4", "#FF4500","#008B8B", "#7B68EE")
                , new Gun(3, 10, 2, "#ffffff", 3, 1, 3), 10);
    }

    @Override
    protected Shop createShop() {
        return new Shop(new Position(23,1), "#FFFFFF");
    }

    @Override
    protected List<Enemy> createEnemies(int width, int height) {
        List<Enemy> enemies = new ArrayList<>();
        boolean found = false;
        for (String line : lines) {
            if (!line.isEmpty() && line.charAt(0) == 'E') found = true;

            if (found) {
                if (line.isEmpty() || line.charAt(0) != 'E') break;
                String[] lineArray = line.split(" ");

                Enemy enemy;

                switch (lineArray[0]) {
                    case "ES" -> enemy = new SmallEnemy();
                    case "EM" -> enemy = new MediumEnemy();
                    case "EB" -> enemy = new BigEnemy();
                    default -> {
                        continue;
                    }
                }

                int damage = 0;
                int speed = 0;
                switch (lineArray[1]) {
                    case "W" -> {
                        damage = 1;
                        speed = 1;
                    }
                    case "M" -> {
                        damage = 2;
                        speed = 2;
                    }
                    case "S" -> {
                        damage = 3;
                        speed = 3;
                    }
                    default -> System.out.println("");//TODO
                }
                enemy.loadDamage(damage);
                enemy.loadSpeed(speed);

                enemy.setPosition(new Position(Integer.parseInt(lineArray[2]),
                        Integer.parseInt(lineArray[3])));

                enemy.setEnergy(Integer.parseInt(lineArray[4]));
                enemy.setBounty(Integer.parseInt(lineArray[5]));
                enemy.setSpeed(Integer.parseInt(lineArray[6]));

                enemies.add(enemy);
            }
        }
        return enemies;
    }

    @Override
    protected List<Portal> createPortals() {
        List<Portal> portals = new ArrayList<>();
        boolean found = false;
        for (String line : lines) {
            if (!line.isEmpty() && line.charAt(0) == 'P') found = true;

            if (found) {
                if (line.isEmpty() || line.charAt(0) != 'P') break;
                String[] lineArray = line.split(" ");

                Position p1 = new Position(Integer.parseInt(lineArray[1]), Integer.parseInt(lineArray[2]));
                Position p2 = new Position(Integer.parseInt(lineArray[3]), Integer.parseInt(lineArray[4]));

                portals.addAll(Portal.createPortals(p1, p2));
            }
        }
        return portals;
    }

    @Override
    protected List<Bomb> createBombs() {
        List<Bomb> bombs = new ArrayList<>();
        boolean found = false;
        for (String line : lines) {
            if (!line.isEmpty() && line.charAt(0) == 'B') found = true;

            if (found) {
                if (line.isEmpty() || line.charAt(0) != 'B') break;
                String[] lineArray = line.split(" ");

                bombs.add(new Bomb(new Position(Integer.parseInt(lineArray[1]), Integer.parseInt(lineArray[2])),
                        "#777777",
                        Integer.parseInt(lineArray[3])));
            }
        }
        return bombs;
    }

    @Override
    protected List<Obstacle> createObstaclesS() {
        List<Obstacle> obstacles = new ArrayList<>();
        boolean found = false;
        for (String line : lines) {
            if (!line.isEmpty() && line.charAt(0) == 'O') found = true;

            if (found) {
                if (line.isEmpty() || line.charAt(0) != 'O') break;
                String[] lineArray = line.split(" ");

                obstacles.add(new Obstacle(new Position(Integer.parseInt(lineArray[1]), Integer.parseInt(lineArray[2])),
                        "#FFA500",
                        Integer.parseInt(lineArray[3])));
            }
        }
        return obstacles;
    }
}
