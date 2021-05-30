package com.g57.controller;

import com.g57.Game;

import java.io.IOException;

public abstract class Controller<T> {
    private T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public abstract void step(Game game, long time) throws IOException;
}
