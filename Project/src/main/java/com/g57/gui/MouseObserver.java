package com.g57.gui;

import com.g57.model.Position;
import com.g57.state.listener.MouseListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MouseObserver extends MouseAdapter {
    private MouseListener listener;

    public MouseObserver(){}

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            try {
                listener.click(new Position(e.getX(), e.getY()));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        listener.move(new Position(e.getX(), e.getY()));
    }

    public void setListener(MouseListener listener) {
        this.listener = listener;
    }

}
