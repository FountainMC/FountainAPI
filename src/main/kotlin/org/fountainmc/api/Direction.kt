package org.fountainmc.api

enum class Direction(val isVertical: Boolean = false) {
    NORTH,
    SOUTH,
    EAST,
    WEST,
    UP(true),
    DOWN(true)
}
