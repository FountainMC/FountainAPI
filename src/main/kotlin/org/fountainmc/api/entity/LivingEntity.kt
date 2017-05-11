package org.fountainmc.api.entity

import org.fountainmc.api.entity.data.EntityData
import org.fountainmc.api.entity.data.LivingEntityData

/**
 * An entity that is alive.
 */
interface LivingEntity : Entity, LivingEntityData {

    /**
     * Damage the Entity

     * @param amount amount of health to take away from the Entity
     */
    fun damage(amount: Double)

    /**
     * Copy all of the given data to this entity.
     *
     * Doesn't copy passenger information.

     * @param data the data to copy from
     */
    override fun copyDataFrom(data: EntityData) {
        super<LivingEntityData>.copyDataFrom(data)
    }


    /**
     * Take a snapshot of this entity's data
     *
     * The resulting snapshot is thread-safe.

     * @return a snapshot
     */
    override fun snapshot(): LivingEntityData
}
