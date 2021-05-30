package com.g57.gui;

import com.g57.state.listener.KeyBoardListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyBoardObserver extends KeyAdapter {
    private KeyBoardListener listener;

    public KeyBoardObserver() {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> listener.keyPressed(GUI.ACTION.QUIT);
            case KeyEvent.VK_A -> listener.keyPressed(GUI.ACTION.LEFT);
            case KeyEvent.VK_D -> listener.keyPressed(GUI.ACTION.RIGHT);
            case KeyEvent.VK_W -> listener.keyPressed(GUI.ACTION.UP);
            case KeyEvent.VK_S -> listener.keyPressed(GUI.ACTION.DOWN);
            case KeyEvent.VK_P -> listener.keyPressed(GUI.ACTION.SHOP);
        }
    }

    public void setListener(KeyBoardListener listener) {
        this.listener = listener;
    }
}
