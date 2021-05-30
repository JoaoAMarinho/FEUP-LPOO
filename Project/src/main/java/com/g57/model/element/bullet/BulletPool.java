package com.g57.model.element.bullet;

import java.util.LinkedList;
import java.util.Queue;

public class BulletPool {
    private Queue<Bullet> bulletPool;

    public BulletPool() {
        bulletPool = new LinkedList<>();
    }

    public void insertBullet(Bullet bullet){
        bulletPool.add(bullet);
    }

    public Bullet removeBullet(){
        if(bulletPool.isEmpty())
            return new Bullet(0.0,0.0, "#FFFFFF", true);
        else return bulletPool.poll();
    }

}
