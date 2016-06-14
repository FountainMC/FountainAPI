package org.fountainmc.api;

public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST,
    UP(true),
    DOWN(true);
    private final boolean vertical;

    Direction() {
        this(true);
    }

    Direction(boolean vertical) {
        this.vertical = vertical;
    }

    public boolean isVertical() {
        return vertical;
    }
}
