package org.fountainmc.api.world.block;

import org.fountainmc.api.Direction;

public interface Chest extends BlockState, DirectionalBlock {

    public Direction getDirection();

    /**
     * Get a clone of this chest with the given direction
     *
     * @param direction the new direction
     * @return the new chest
     */
    public Chest withDirection(Direction direction);

    @Override
    public default boolean mayBeVertical() {
        return false;
    }

}
