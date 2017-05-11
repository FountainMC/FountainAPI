package org.fountainmc.api.entity.hanging

import org.fountainmc.api.entity.data.EntityData
import org.fountainmc.api.entity.data.hanging.ItemFrameData

/**
 * An ItemFrame that is hanging on a wall
 */
interface ItemFrame : HangingEntity, ItemFrameData {

    /**
     * Copy all of the mutable properties from the given data into this object.
     *
     * Doesn't copy passenger information.
     * Doesn't copy the [direction] the frame is facing.
     *
     * @param data the data to copy from
     */
    override fun copyDataFrom(data: EntityData) {
        super<ItemFrameData>.copyDataFrom(data)
    }

    /**
     * Take a snapshot of this entity's data
     *
     * The resulting snapshot is thread-safe.
     *
     * @return a snapshot
     */
    override fun snapshot(): ItemFrameData

}
