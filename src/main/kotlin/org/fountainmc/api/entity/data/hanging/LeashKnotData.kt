package org.fountainmc.api.entity.data.hanging

import org.fountainmc.api.entity.data.EntityData

interface LeashKnotData : HangingEntityData {

    val leashedEntity: EntityData?

    /**
     * Copy all of the mutable properties from the given data into this object.
     *
     * Doesn't copy [leashedEntity] information,
     * since that may involve interaction with other entities.
     */
    override fun copyDataFrom(data: EntityData) {
        super.copyDataFrom(data)
    }
}
