package org.fountainmc.api.entity.data

import org.fountainmc.api.Server
import org.fountainmc.api.entity.EntityType

/**
 * The data for an entity, which isn't necessarily spawned in a world.
 *
 * Notes:
 *
 *  * this doesn't include the entity's location as that only makes sense for an entity that is in the world.
 *  * this doesn't include information about the riding entity because nbt doesn't contain that
 *  * you can't modify the passenger because it'd be extremely difficult and buggy to implement
 */
interface EntityData {

    /**
     * The server that created this data
     */
    val server: Server

    /**
     * The pitch of the entity
     */
    var pitch: Float

    /**
     * The yaw of the entity
     */
    var yaw: Float

    /**
     * The primary passenger riding on top of this entity
     */
    val primaryPassenger: EntityData?

    val hasPassengers
        get() = passengers.isNotEmpty()

    /**
     * The passengers riding on top of this Entity.
     */
    val passengers: List<EntityData>

    var ticksOnFire: Int

    val isOnFire: Boolean
        get() = ticksOnFire >= 0

    var isImmuneToFire: Boolean

    /**
     * The type of entity this data is intended for
     */
    val entityType: EntityType<*>

    /**
     * Copy all of the mutable properties from the given data into this object.
     *
     * Doesn't copy [passenger information][passengers],
     * as that may involve modification/interaction with other entities.
     *
     * @param data the data to copy from
     */
    fun copyDataFrom(data: EntityData) {
        this.pitch = data.pitch
        this.yaw = data.yaw
        this.ticksOnFire = data.ticksOnFire
        this.isImmuneToFire = data.isImmuneToFire
    }
}
