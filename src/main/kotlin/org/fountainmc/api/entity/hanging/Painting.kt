package org.fountainmc.api.entity.hanging

import org.fountainmc.api.entity.Entity
import org.fountainmc.api.entity.data.EntityData
import org.fountainmc.api.entity.data.hanging.PaintingData

/**
 * A painting that is hanging on a wall.
 */
interface Painting : PaintingData, HangingEntity, Entity {

    /**
     * Copy all of the mutable properties from the given data into this object.
     *
     * @param data the data to copy from
     * @see Entity.copyDataFrom
     * @see PaintingData.copyDataFrom
     */
    override fun copyDataFrom(data: EntityData) {
        super<PaintingData>.copyDataFrom(data)
    }

    /**
     * Take a snapshot of this entity's data
     *
     * The resulting snapshot is thread-safe.
     *
     * @return a snapshot
     */
    override fun snapshot(): PaintingData
}
