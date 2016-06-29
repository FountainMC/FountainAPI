package org.fountainmc.api.entity.data.hanging;

/**
 * A painting's data.
 */
public interface PaintingData extends HangingEntityData {

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
