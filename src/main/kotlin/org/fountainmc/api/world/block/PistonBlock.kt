package org.fountainmc.api.world.block

import org.fountainmc.api.Direction

/**
 * The state for pistons and piston extensions
 */
interface PistonBlock : BlockState, DirectionalBlock {

    /**
     * Return if the piston has been extended

     * @return if extended
     */
    val isExtended: Boolean

    /**
     * Get a clone of this piston with the given extension flag
     *
     * If the piston is an extension ([.isExtension]), then it must always be flagged as 'extended'.

     * @param b if the piston should be extended
     * *
     * @return the new piston
     * *
     * @throws IllegalStateException if the piston is a piston extension as returned by [.isExtension], and therefore must be extended
     */
    fun withExtended(b: Boolean): PistonBlock

    val direction: Direction

    /**
     * Get a clone of this piston with the given direction

     * @param direction the new direction
     * *
     * @return the new piston
     */
    override fun withDirection(direction: Direction): PistonBlock

    override fun mayBeVertical(): Boolean {
        return true
    }

    /**
     * Return if the piston is sticky

     * @return if sticky
     */
    val isSticky: Boolean

    /**
     * Get a clone of this piston with the given stickiness setting

     * @param sticky if the new piston should be sticky
     * *
     * @return the new piston
     */
    fun withStickiness(sticky: Boolean): PistonBlock

    /**
     * Get this piston as a sticky piston if it isn't already one

     * @return this piston as a sticky piston
     */
    fun asSticky(): PistonBlock {
        return withStickiness(true)
    }

    /**
     * Get this piston as a non-sticky piston if it isn't already one

     * @return this piston as a non-sticky piston
     */
    fun asNonSticky(): PistonBlock {
        return withStickiness(false)
    }

    /**
     * Return if the block is a piston extension
     *
     * A piston extension is that arm thingymagig that shoots out of an activated piston.
     *
     * Implies that the piston is extended, and [.isExtended]} should return true.

     * @return if the block is an extension
     */
    val isExtension: Boolean

}
