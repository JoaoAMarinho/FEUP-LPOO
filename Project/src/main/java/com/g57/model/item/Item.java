package com.g57.model.item;

import java.util.Collections;
import java.util.List;

public abstract class Item {
    protected final int price;
    protected final String color;

    public Item(int price,String color) {
        this.price = price;
        this.color=color;
    }

    public int getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    @Override
    public abstract boolean equals(Object o);

    public List<String> getCharacteristics(){
        return (price >= 10) ? Collections.singletonList(price + "b") : Collections.singletonList(" " + price + "b");
    }
}
