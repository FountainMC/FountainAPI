package org.fountainmc.api.entity.hanging;

/**
 * A painting that is hanging on a wall.
 */
public interface Painting extends HangingEntity {

    /**
     * Get the width of the painting in blocks.
     * 
     * @return the width of the painting
     */
    int getWidth();

    /**
     * Get the height of the painting in blocks.
     * 
     * @return the height of the painting
     */
    int getHeight();

}
