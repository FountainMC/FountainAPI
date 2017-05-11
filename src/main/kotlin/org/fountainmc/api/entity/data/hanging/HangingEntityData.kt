package org.fountainmc.api.entity.data.hanging

import org.fountainmc.api.Direction
import org.fountainmc.api.entity.data.EntityData

interface HangingEntityData : EntityData {

    /**
     * The direction the Entity is facing
     *
     * The Direction will be null for Entities like LeashKnots.
     */
    val direction: Direction?

    /**
     * Copy all of the mutable properties from the given data into this object.
     *
     * The [direction] won't be copied, as it can't be sensibly modified for existing entities.
     */
    override fun copyDataFrom(data: EntityData) {
        super.copyDataFrom(data)
    }
}
