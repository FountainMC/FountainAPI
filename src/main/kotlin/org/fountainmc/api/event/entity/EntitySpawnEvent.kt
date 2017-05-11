package org.fountainmc.api.event.entity

import org.fountainmc.api.entity.Entity
import org.fountainmc.api.event.Cancellable
import org.fountainmc.api.world.Location
import javax.annotation.ParametersAreNonnullByDefault

@ParametersAreNonnullByDefault
interface EntitySpawnEvent : EntityEvent, Cancellable {

    val location: Location

    companion object {

        @JvmOverloads
        fun create(entity: Entity, location: Location = entity.location): EntitySpawnEvent {
            require(entity.world == location.world) {
                "Entity's world ${entity.world} doesn't match location's world ${location.world}"
            }
            class SimpleEntitySpawnEvent(
                    override val location: Location,
                    override val entity: Entity
            ) : Cancellable, EntitySpawnEvent {
                override var isCancelled = false
            }
            return SimpleEntitySpawnEvent(location, entity)
        }
    }

}
