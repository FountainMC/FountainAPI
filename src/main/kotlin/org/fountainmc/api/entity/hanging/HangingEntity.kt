package org.fountainmc.api.entity.hanging

import org.fountainmc.api.entity.Entity
import org.fountainmc.api.entity.data.EntityData
import org.fountainmc.api.entity.data.hanging.HangingEntityData

/**
 * An entity handing on a wall or other object.
 */
interface HangingEntity : HangingEntityData, Entity {
    /**
     * Copy all of the given data to this entity.
     *
     * Doesn't copy passenger information.
     *
     * @param data the data to copy from
     */
    override fun copyDataFrom(data: EntityData) {
        super<HangingEntityData>.copyDataFrom(data)
    }

    /**
     * Take a snapshot of this entity's data
     *
     * The resulting snapshot is thread-safe.

     * @return a snapshot
     */
    override fun snapshot(): HangingEntityData

}
