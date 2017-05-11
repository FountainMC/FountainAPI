package org.fountainmc.api.entity.data.hanging

import org.fountainmc.api.entity.data.EntityData

/**
 * A painting's data.
 */
interface PaintingData : HangingEntityData {

    /**
     * Get the width of the painting in blocks.

     * @return the width of the painting
     */
    val width: Int

    /**
     * Get the height of the painting in blocks.

     * @return the height of the painting
     */
    val height: Int

    /**
     * Copy all of the mutable properties from the given data into this object.
     *
     * The [width] and [height] won't be copied,
     * as it can't be sensibly modified for existing paintings.
     */
    override fun copyDataFrom(data: EntityData) {
        super.copyDataFrom(data)
    }
}
