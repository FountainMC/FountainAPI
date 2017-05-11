package org.fountainmc.api.entity.data

import org.fountainmc.api.entity.EntityType
import org.fountainmc.api.entity.LivingEntity

/**
 * The data of a [org.fountainmc.api.entity.LivingEntity]
 */
interface LivingEntityData : EntityData {

    /**
     * The health of the entity
     */
    var health: Double

    /**
     * The maximum health this entity can have.
     */
    var maxHealth: Double

    override val entityType: EntityType<LivingEntity>

    override fun copyDataFrom(data: EntityData) {
        super.copyDataFrom(data)
        if (data is LivingEntityData) {
            this.health = data.health
            this.maxHealth = data.maxHealth
        }
    }

}
