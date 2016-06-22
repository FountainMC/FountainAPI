package org.fountainmc.api.world.block;

import javax.annotation.Nonnull;

import org.fountainmc.api.Direction;

/**
 * The state for pistons and piston extensions
 */
@Nonnull
public interface PistonBlock extends BlockState, DirectionalBlock {

    /**
     * Return if the piston has been extended
     *
     * @return if extended
     */
    boolean isExtended();

    /**
     * Get a clone of this piston with the given extension flag
     * <p>If the piston is an extension ({@link #isExtension()}), then it must always be flagged as 'extended'.</p>
     *
     * @param b if the piston should be extended
     * @return the new piston
     * @throws IllegalStateException if the piston is a piston extension as returned by {@link #isExtension()}, and therefore must be extended
     */
    PistonBlock withExtended(boolean b);

    Direction getDirection();

    /**
     * Get a clone of this piston with the given direction
     *
     * @param direction the new direction
     * @return the new piston
     */
    PistonBlock withDirection(Direction direction);

    @Override
    default boolean mayBeVertical() {
        return true;
    }

    /**
     * Return if the piston is sticky
     *
     * @return if sticky
     */
    boolean isSticky();

    /**
     * Get a clone of this piston with the given stickiness setting
     *
     * @param sticky if the new piston should be sticky
     * @return the new piston
     */
    PistonBlock withStickiness(boolean sticky);

    /**
     * Get this piston as a sticky piston if it isn't already one
     *
     * @return this piston as a sticky piston
     */
    default PistonBlock asSticky() {
        return withStickiness(true);
    }

    /**
     * Get this piston as a non-sticky piston if it isn't already one
     *
     * @return this piston as a non-sticky piston
     */
    default PistonBlock asNonSticky() {
        return withStickiness(false);
    }

    /**
     * Return if the block is a piston extension
     * <p>A piston extension is that arm thingymagig that shoots out of an activated piston.</p>
     * <p>Implies that the piston is extended, and {@link #isExtended()}} should return true.</p>
     *
     * @return if the block is an extension
     */
    boolean isExtension();

}
