package org.fountainmc.api.event.entity

import com.google.common.base.Preconditions.*
import org.fountainmc.api.entity.Entity
import org.fountainmc.api.event.Cancellable
import org.fountainmc.api.world.Location
import javax.annotation.ParametersAreNonnullByDefault

@ParametersAreNonnullByDefault
interface EntityRemovedEvent : EntityEvent, Cancellable {

    val location: Location

    companion object {

        @JvmOverloads
        fun create(entity: Entity, location: Location = entity.location): EntityRemovedEvent {
            checkArgument(entity.world == checkNotNull(location, "Null location").world, "Entity's world %s doesn't match location's world %s", entity.world, location.world)
            class SimpleEntityRemovedEvent(
                    override val location: Location,
                    override val entity: Entity
            ) : Cancellable, EntityRemovedEvent {
                override var isCancelled = false
            }
            return SimpleEntityRemovedEvent(location, entity)
        }
    }
}
