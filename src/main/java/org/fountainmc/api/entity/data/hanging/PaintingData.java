package org.fountainmc.api.entity.data.hanging;

import org.fountainmc.api.entity.data.EntityData;

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

    @Override
    default void copyDataFrom(EntityData data) {
        HangingEntityData.super.copyDataFrom(data);
        // if we ever get anything to copy add it here
    }
}
