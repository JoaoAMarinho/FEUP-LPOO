package com.g57.controller;

import com.g57.model.battlefield.Battlefield;

public abstract class GameController extends Controller<Battlefield> {
    public GameController(Battlefield battlefield) { super(battlefield); }
}
