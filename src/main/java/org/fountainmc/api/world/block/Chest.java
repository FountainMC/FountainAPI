package org.fountainmc.api.world.block;

import org.fountainmc.api.Direction;

public interface Chest extends BlockState, DirectionalBlock {

    Direction getDirection();

    /**
     * Get a clone of this chest with the given direction
     *
     * @param direction the new direction
     * @return the new chest
     */
    Chest withDirection(Direction direction);

    @Override
    default boolean mayBeVertical() {
        return false;
    }

}
