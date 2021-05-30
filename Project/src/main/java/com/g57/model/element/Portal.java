package com.g57.model.element;

import com.g57.model.Position;

import java.util.Arrays;
import java.util.List;

public class Portal extends Element {
    private Portal dest;

    // Não permite criar portais sem um correspondente
    public Portal(Position position) {
        super(position, "#FF0000");
    }

    // Cria par de portais
    public static List<Portal> createPortals(Position org, Position des) {
        //Dá erro se as posições forem iguais
        Portal orig = new Portal(org);
        Portal dest = new Portal(des);
        orig.setDest(dest);
        dest.setDest(orig);
        return Arrays.asList(orig, dest);
    }

    public Portal getDest() {
        return dest;
    }
    public void setDest(Portal dest) {
        this.dest = dest;
    }
    public Position getLeavePos(int width, int height){
        if(position.getX()==0) return new Position(1, position.getY());
        if(position.getY()==0) return new Position(position.getX(), 1);
        if(position.getX()==width-1) return new Position(width-2, position.getY());
        if(position.getY()==height-1) return new Position(position.getX(), height-2);
        return null;
    }
}
