package org.fountainmc.api.world.block;

import org.fountainmc.api.Direction;

public interface Chest extends BlockState {

    public Direction getDirection();

    /**
     * Get a clone of this chest with the given direction
     *
     * @param direction the new direction
     * @return the new piston
     */
    public PistonBlock withDirection(Direction direction);

}
