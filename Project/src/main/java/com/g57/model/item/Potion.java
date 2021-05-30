package com.g57.model.item;

import java.util.Objects;

public abstract  class Potion extends Item {
    protected final int duration;   //Potion duration in milliseconds
    protected long startTime;       //Potion Start Time
    protected final int increaser;  //Potion increase value

    public Potion(int price, String color, int increaser, int duration) {
        super(price, color);
        this.duration = duration;
        this.startTime = 0;
        this.increaser = increaser;
    }

    public int getDuration() {
        return duration;
    }

    public long getStartTime() {
        return startTime;
    }

    public int getIncreaser() {
        return increaser;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(increaser);
    }
}
