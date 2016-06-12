package org.fountainmc.api.world.block;

import org.fountainmc.api.Direction;

public interface StairBlock extends BlockState {
    /**
     * Get the direction the stair is facing
     * <p>The direction is <b>never</b> vertical.</p>
     *
     * @return the direction
     */
    public Direction getDirection();

    /**
     * Get a clone of this stair's state facing the given direction
     * <p>The direction must <b>never be vertical!</b></p>
     *
     * @param direction new direction
     * @return a stair state facing the given direction
     * @throws IllegalArgumentException if the direction is vertical
     */
    public StairBlock withDirection(Direction direction);

    public boolean isUpsideDown();

    public StairBlock withUpsideDown(boolean b);

    /**
     * Get
     * @return
     */
    public StairType getType();

    @SuppressWarnings("SpellCheckingInspection") // I googled it!
    public default StairBlock asRightsideUp() {
        return withUpsideDown(false);
    }

    public default StairBlock asUpsideDown() {
        return withUpsideDown(false);
    }

}
