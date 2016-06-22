package org.fountainmc.api.world.block;

import org.fountainmc.api.Direction;

public interface StairBlock extends BlockState, DirectionalBlock {

    /**
     * Get the direction the stair is facing
     * <p>The direction is <b>never</b> vertical.</p>
     *
     * @return the direction
     */
    Direction getDirection();

    /**
     * Get a clone of this stair's state facing the given direction
     * <p>The direction must <b>never be vertical!</b></p>
     *
     * @param direction new direction
     * @return a stair state facing the given direction
     * @throws IllegalArgumentException if the direction is vertical
     */
    StairBlock withDirection(Direction direction);

    @Override
    default boolean mayBeVertical() {
        return false;
    }

    boolean isUpsideDown();

    StairBlock withUpsideDown(boolean b);

    /**
     * Get the type of the stairs
     *
     * @return the type
     */
    StairType getType();

    default StairBlock asRightsideUp() {
        return withUpsideDown(false);
    }

    default StairBlock asUpsideDown() {
        return withUpsideDown(false);
    }

}
