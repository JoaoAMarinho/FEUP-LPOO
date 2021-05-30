package com.g57.model.element.button;

import com.g57.model.Position;
import com.g57.model.element.Element;
import com.g57.model.item.command.Command;

import java.util.List;

public class Button extends Element {
    private final int width;
    private final int height;
    private final String text;
    private String bgColor;
    private final Position textPosition;
    private Command command;
    protected Boolean active = false;

    private final List<String> colors;
    private int colorIndex = 0;

    public Button(Position bPos, Position tPos, String text, int width, int height, Command command, List<String> colors) {
        super(bPos, colors.get(0));
        this.command = command;
        this.colors = colors;
        this.width = width;
        this.height = height;
        this.text = text;
        this.bgColor = "#000000";
        this.textPosition = tPos;
    }

    public Button(Position position, Command command, List<String> colors) {
        super(position, colors.get(0));
        this.command = command;
        this.colors = colors;
        this.text = "";
        this.width = 1;
        this.height = 1;
        this.bgColor = "#000000";
        this.textPosition = position;
    }

    public Button(Position position, String text, Command command, List<String> colors) {
        super(position, colors.get(0));
        this.command = command;
        this.colors = colors;
        this.text = text;
        this.width = text.length();
        this.height = 1;
        this.bgColor = "#000000";
        this.textPosition = position;
    }

    public Position getTextPosition() {
        return textPosition;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getText() {
        return text;
    }

    public void changeColor() {
        this.colorIndex++;
        this.colorIndex= this.colorIndex % this.colors.size();
        this.color = this.colors.get(this.colorIndex);
    }

    public boolean isHighlighted() {
        return this.color.equals(this.colors.get(1));
    }

    public void toneDown() {
        this.color = this.colors.get(0);
    }

    public static int getButtonX(int screenWidth, String text) {
        int middle = text.length() / 2;
        return screenWidth / 2 - middle;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void highlight() {
        this.color = this.colors.get(1);
    }

    public Command getCommand() {
        return command;
    }

    public boolean isActive() {
        return active;
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

}
