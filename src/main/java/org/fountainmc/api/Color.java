package org.fountainmc.api;

public enum Color {
    WHITE,
    ORANGE,
    MAGENTA,
    LIGHT_BLUE,
    YELLOW,
    LIME,
    PINK,
    GREY, // Use british spelling because it makes more sense
    LIGHT_GREY,
    CYAN,
    PURPLE,
    BLUE,
    BROWN,
    GREEN,
    RED,
    BLACK;

    public int getWoolId() {
        return ordinal();
    }

}
