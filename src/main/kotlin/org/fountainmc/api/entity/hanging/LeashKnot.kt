package org.fountainmc.api.entity.hanging

import org.fountainmc.api.entity.Entity
import org.fountainmc.api.entity.data.EntityData
import org.fountainmc.api.entity.data.hanging.LeashKnotData

interface LeashKnot : HangingEntity, LeashKnotData {

    override val leashedEntity: Entity?

    /**
     * Copy all of the given data to this entity.
     *
     * Doesn't copy [leashedEntity] information,
     * since that may involve interaction with other entities.
     *
     * @param data the data to copy from
     */
    override fun copyDataFrom(data: EntityData) {
        super<LeashKnotData>.copyDataFrom(data)
    }

    /**
     * Take a snapshot of this entity's data
     *
     * The resulting snapshot is thread-safe.
     *
     * @return a snapshot
     */
    override fun snapshot(): LeashKnotData
}
