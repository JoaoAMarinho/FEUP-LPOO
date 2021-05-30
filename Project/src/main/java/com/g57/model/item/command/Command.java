package com.g57.model.item.command;

public interface Command {
    boolean execute();
    void undo();
}
